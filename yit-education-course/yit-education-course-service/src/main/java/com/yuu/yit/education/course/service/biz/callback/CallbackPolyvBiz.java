/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.course.service.biz.callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.course.service.dao.CourseChapterDao;
import com.yuu.yit.education.course.service.dao.CourseChapterPeriodDao;
import com.yuu.yit.education.course.service.dao.CourseDao;
import com.yuu.yit.education.course.service.dao.OrderInfoDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Course;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderInfo;
import com.yuu.yit.education.user.common.bean.vo.UserExtVO;
import com.yuu.yit.education.user.feign.IBossUserExt;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.enums.IsFreeEnum;
import com.yuu.yit.education.util.enums.OrderStatusEnum;
import com.yuu.yit.education.util.polyv.PolyvAuth;
import com.yuu.yit.education.util.polyv.PolyvAuthResult;
import com.yuu.yit.education.util.polyv.PolyvCode;
import com.yuu.yit.education.util.polyv.PolyvUtil;
import com.yuu.yit.education.util.polyv.PolyvVideo;
import com.yuu.yit.education.util.tools.JSONUtil;
import com.yuu.yit.education.util.tools.MD5Util;

/**
 * 课时信息-审核
 *
 * @author Yuu123
 */
@Component
public class CallbackPolyvBiz extends BaseBiz {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseChapterDao courseChapterDao;
	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;
	@Autowired
	private OrderInfoDao orderInfoDao;

	@Autowired
	private IBossUserExt bossUserExt;

	/**
	 * 保利威视，视频上传回调接口
	 * 
	 * @param polyvVideo
	 * @return
	 * @author Yuu
	 */
	public String video(PolyvVideo polyvVideo) {
		if (checkSign(polyvVideo.getSign(), polyvVideo.getType(), polyvVideo.getVid(), polyvVideo.getSecretkey())) {
			if ("pass".equalsIgnoreCase(polyvVideo.getType())) {
				logger.warn("保利威视-上传视频-回调成功：{}", polyvVideo);
				return "success";
			}
		}
		return "fail";
	}

	/**
	 * 保利威视，视频授权播放回调接口 解密保利威视返回code值，获取到用户编号和课时编号
	 * 
	 * @param polyvAuth
	 * @return
	 * @author Yuu
	 */
	public String auth(PolyvAuth polyvAuth) {
		logger.warn("保利威视，播放授权回调接口，接收参数={}", polyvAuth);

		PolyvAuthResult result = new PolyvAuthResult();
		// 解密
		PolyvCode polyvCode = PolyvUtil.decode(polyvAuth.getCode());
		if (null == polyvCode) {
			result.setStatus(2);
			result.setMsg("系统异常，请重试");
			return resp(result, polyvAuth);
		}

		// 用户校验
		UserExtVO userInfo = bossUserExt.getByUserNo(polyvCode.getUserNo());
		if (null == userInfo) {
			result.setStatus(2);
			result.setMsg("用户账号异常");
			return resp(result, polyvAuth);
		}

		// 课时校验
		CourseChapterPeriod periodInfo = courseChapterPeriodDao.getById(polyvCode.getPeriodNo());

		// 章节校验
		CourseChapter chapterInfo = courseChapterDao.getById(periodInfo.getChapterId());

		// 课程校验
		Course courseInfo = courseDao.getById(periodInfo.getCourseId());

		// 课程、章节、课时任一个为免费该课时都可以免费观看
		if (IsFreeEnum.FREE.getCode().equals(periodInfo.getIsFree()) || IsFreeEnum.FREE.getCode().equals(chapterInfo.getIsFree()) || IsFreeEnum.FREE.getCode().equals(courseInfo.getIsFree())) {
			// 免费课时，可以播放
			result.setStatus(1);
			result.setMsg("该课时可以免费观看");
			result.setUsername(getUsername(userInfo.getMobile(), userInfo.getUserNo()));
			return resp(result, polyvAuth);
		}

		// 收费课程
		// 是否购买了该课程
		OrderInfo ref = orderInfoDao.getByUserNoAndCourseId(polyvCode.getUserNo(), periodInfo.getCourseId());

		if (null == ref) {
			result.setStatus(2);
			result.setMsg("该课时为收费课程，请先购买");
			return resp(result, polyvAuth);
		}

		// 没支付
		if (!OrderStatusEnum.SUCCESS.getCode().equals(ref.getOrderStatus())) {
			result.setStatus(2);
			result.setMsg("该课时为收费课程，请先购买");
			return resp(result, polyvAuth);
		}

		// 支付成功
		result.setStatus(1);
		result.setMsg("允许播放");
		result.setUsername(getUsername(userInfo.getMobile(), userInfo.getUserNo()));
		return resp(result, polyvAuth);
	}

	private String resp(PolyvAuthResult result, PolyvAuth polyvAuth) {
		result.setSign(MD5Util.MD5("vid=" + polyvAuth.getVid() + "&secretkey=" + polyvAuth.getSecretkey() + "&username=" + result.getUsername() + "&code=" + polyvAuth.getCode() + "&status=" + result.getStatus() + "&t=" + polyvAuth.getT()));
		StringBuffer sb = new StringBuffer();
		if (StringUtils.hasText(polyvAuth.getCallback())) {
			sb.append(polyvAuth.getCallback()).append("(").append(JSONUtil.toJSONString(result)).append(")");
		} else {
			// flash
			sb.append(JSONUtil.toJSONString(result));
		}
		logger.warn("保利威视，播放授权回调接口，返回报文={}", sb);
		return sb.toString();
	}

	private String getUsername(String mobile, Long userNo) {
		return mobile;
	}

	// sign值校验
	public static boolean checkSign(String sign, String type, String vid, String secretkey) {
		StringBuilder sb = new StringBuilder();
		String argSign = MD5Util.MD5(sb.append("manage").append(type).append(vid).append(secretkey).toString());
		if (argSign.equals(sign)) {
			return true;
		}
		return false;
	}
}
