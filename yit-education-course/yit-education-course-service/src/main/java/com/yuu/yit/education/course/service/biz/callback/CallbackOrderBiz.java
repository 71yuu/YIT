/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.course.service.biz.callback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.course.service.common.bo.OrderInfoPayNotifyBO;
import com.yuu.yit.education.course.service.dao.CourseDao;
import com.yuu.yit.education.course.service.dao.OrderInfoDao;
import com.yuu.yit.education.course.service.dao.OrderPayDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Course;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.yuu.yit.education.user.common.bean.qo.LecturerExtQO;
import com.yuu.yit.education.user.common.bean.vo.LecturerExtVO;
import com.yuu.yit.education.user.common.bean.vo.LecturerVO;
import com.yuu.yit.education.user.feign.IBossLecturer;
import com.yuu.yit.education.user.feign.IBossLecturerExt;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.OrderStatusEnum;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 订单信息表
 *
 * @author Yuu123
 */
@Component
public class CallbackOrderBiz extends BaseBiz {

	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private OrderPayDao orderPayDao;
	@Autowired
	private CourseDao courseDao;

	@Autowired
	private IBossLecturer bossLecturer;
	@Autowired
	private IBossLecturerExt bossLecturerExt;

	/**
	 * 订单异步通知接口
	 * 
	 * @param orderInfoPayBO
	 * @return
	 * @author Yuu
	 */
	@Transactional
	public Result<String> payNotify(OrderInfoPayNotifyBO notifyBO) {
		if (StringUtils.isEmpty(notifyBO.getSerialNumber())) {
			Result.error("传入的订单流水号不能为空!");
		}

		if (StringUtils.isEmpty(notifyBO.getOrderStatus())) {
			Result.error("传入的交易状态不能为空!");
		}

		// 根据订单交易流水号查找订单信息
		OrderPay orderPay = orderPayDao.getBySerialNumber(notifyBO.getSerialNumber());
		// 根据订单号查找订单信息
		OrderInfo orderInfo = orderInfoDao.getByOrderNo(orderPay.getOrderNo());

		if (StringUtils.isEmpty(orderPay)) {
			Result.error("根据传入的交易订单流水号,没找到对应的订单信息!");
		}
		if (StringUtils.isEmpty(orderInfo)) {
			Result.error("根据传入的交易订单号,没找到对应的订单信息!");
		}

		// 如果订单状态不是待支付状态证明订单已经处理过,不用再处理
		if (!OrderStatusEnum.WAIT.getCode().equals(orderPay.getOrderStatus())) {
			return Result.success("success");
		}

		// 订单状态为成功时处理
		if (OrderStatusEnum.SUCCESS.getCode().equals(notifyBO.getOrderStatus())) {
			// 处理课程信息
			return course(orderInfo, orderPay);
		} else if (OrderStatusEnum.FAIL.getCode().equals(notifyBO.getOrderStatus())) {
			// 更新订单信息
			orderInfo.setOrderStatus(OrderStatusEnum.FAIL.getCode());
			orderInfoDao.updateById(orderInfo);
			// 更新订单支付信息
			orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
			orderPayDao.updateById(orderPay);
		}

		// 这里是指处理成功
		return Result.success("success");
	}

	/**
	 * 课程处理
	 * 
	 * @param orderInfo
	 * @param orderPay
	 * @return
	 * @author Yuu
	 */
	private Result<String> course(OrderInfo orderInfo, OrderPay orderPay) {
		// 根据课程No查找课程信息
		Course course = courseDao.getById(orderInfo.getCourseId());
		if (StringUtils.isEmpty(course)) {
			Result.error("根据订单的课程编号,没找到对应的课程信息!");
		}

		// 根据讲师编号和机构编号查找讲师账户信息
		LecturerExtVO lecturerExtVO = bossLecturerExt.getByLecturerUserNo(course.getLecturerUserNo());
		if (StringUtils.isEmpty(lecturerExtVO)) {
			Result.error("找不到讲师账户信息!");
		}

		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(course.getLecturerUserNo());
		if (ObjectUtil.isNull(lecturerVO)) {
			return Result.error("找不到讲师信息");
		}

		// 更新课程信息的购买人数
		course.setCountBuy(course.getCountBuy() + 1);
		courseDao.updateById(course);

		// 计算讲师分润
		orderInfo = countProfit(orderInfo, lecturerExtVO, lecturerVO.getLecturerProportion());
		orderInfo.setPayTime(new Date());
		orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());

		// 更新讲师账户信息
		updateLecturerExtVO(orderInfo, lecturerExtVO);

		// 更新订单信息
		orderInfoDao.updateById(orderInfo);
		
		// 更新订单支付信息
		orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		orderPay.setPayTime(new Date());
		orderPayDao.updateById(orderPay);

		return Result.success("success");
	}

	/**
	 * 计算处理讲师分润信息
	 * 
	 * @param orderInfo
	 * @param lecturerExtVO
	 * @param lecturerProportion
	 * @return
	 * @author Yuu
	 */
	private OrderInfo countProfit(OrderInfo orderInfo, LecturerExtVO lecturerExtVO, BigDecimal lecturerProportion) {
		// 讲师收入 = 订单价格x讲师分成比例
		BigDecimal lecturerProfit = orderInfo.getPricePaid().multiply(lecturerProportion).setScale(2, RoundingMode.DOWN);
		// 平台收入 = 实付金额 - 讲师收入
		BigDecimal platformIncome = orderInfo.getPricePaid().subtract(lecturerProfit).setScale(2, RoundingMode.DOWN);

		orderInfo.setLecturerProfit(lecturerProfit);
		orderInfo.setPlatformProfit(platformIncome);

		return orderInfo;
	}

	/**
	 * 更新讲师账户信息
	 * 
	 * @param orderInfo
	 * @param lecturerAccoutVO
	 * @author Yuu
	 */
	private void updateLecturerExtVO(OrderInfo orderInfo, LecturerExtVO lecturerExtVO) {
		LecturerExtQO lecturerExtQO = new LecturerExtQO();
		lecturerExtQO.setLecturerUserNo(lecturerExtVO.getLecturerUserNo());
		lecturerExtQO.setTotalIncome(orderInfo.getLecturerProfit());
		lecturerExtQO.setEnableBalances(orderInfo.getLecturerProfit());
		bossLecturerExt.updateTotalIncomeByLecturerUserNo(lecturerExtQO);
	}

}
