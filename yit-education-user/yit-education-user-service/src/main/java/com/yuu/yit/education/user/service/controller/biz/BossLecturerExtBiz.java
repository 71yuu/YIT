package com.yuu.yit.education.user.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.user.common.bean.qo.LecturerExtQO;
import com.yuu.yit.education.user.common.bean.vo.LecturerExtVO;
import com.yuu.yit.education.user.service.dao.LecturerExtDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerExtExample;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.BaseException;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.yuu.yit.education.util.tools.SignUtil;

/**
 * 讲师账户信息表
 *
 * @author Yuu
 */
@Component
public class BossLecturerExtBiz extends BaseBiz {

	@Autowired
	private LecturerExtDao dao;

	public Page<LecturerExtVO> listForPage(LecturerExtQO qo) {
		LecturerExtExample example = new LecturerExtExample();
		example.setOrderByClause(" id desc ");
		Page<LecturerExt> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, LecturerExtVO.class);
	}

	public int save(LecturerExtQO qo) {
		LecturerExt record = BeanUtil.copyProperties(qo, LecturerExt.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public LecturerExtVO getById(Long id) {
		LecturerExt record = dao.getById(id);
		return BeanUtil.copyProperties(record, LecturerExtVO.class);
	}

	public int updateById(LecturerExtQO qo) {
		LecturerExt record = BeanUtil.copyProperties(qo, LecturerExt.class);
		return dao.updateById(record);
	}

	/**
	 * 根据讲师编号查找账户信息（订单回调远程调用）
	 * 
	 * @param lecturerUserNo
	 * @return
	 * @author Yuu
	 */
	public LecturerExtVO getByLecturerUserNo(Long lecturerUserNo) {
		LecturerExt record = dao.getByLecturerUserNo(lecturerUserNo);
		return BeanUtil.copyProperties(record, LecturerExtVO.class);
	}

	/**
	 * 更新账户的金额信息(订单回调远程调用)
	 * 
	 * @param qo
	 * @author Yuu
	 */
	public void updateTotalIncomeByLecturerUserNo(LecturerExtQO qo) {
		// 查询出讲师账户原来的收入信息
		LecturerExt lecturerExt = dao.getByLecturerUserNo(qo.getLecturerUserNo());
		String sign = SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances());
		// sign值校验
		if (lecturerExt.getSign().equals(sign)) {
			lecturerExt.setTotalIncome(lecturerExt.getTotalIncome().add(qo.getEnableBalances()));// 总收入+订单传过来的可提余额
			lecturerExt.setEnableBalances(lecturerExt.getEnableBalances().add(qo.getEnableBalances()));// 可提余额+订单传过来的可提余额
			lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
			dao.updateByLecturerUserNo(lecturerExt);
		} else {
			logger.error("账户异常 , 用户编号={}" + lecturerExt.getLecturerUserNo());
			throw new BaseException("账户异常");
		}
	}

}
