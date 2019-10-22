package com.yuu.yit.education.user.service.biz.auth;

import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerAuditBO;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerAuditSaveBO;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerAuditViewBO;
import com.yuu.yit.education.user.service.common.dto.auth.AuthLecturerAuditViewDTO;
import com.yuu.yit.education.user.service.dao.LecturerAuditDao;
import com.yuu.yit.education.user.service.dao.LecturerExtDao;
import com.yuu.yit.education.user.service.dao.UserDao;
import com.yuu.yit.education.user.service.dao.UserExtDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerAudit;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.User;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.UserExt;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.BaseException;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.AuditStatusEnum;
import com.yuu.yit.education.util.enums.ResultEnum;
import com.yuu.yit.education.util.enums.StatusIdEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.yuu.yit.education.util.tools.NOUtil;
import com.yuu.yit.education.util.tools.SignUtil;
import com.yuu.yit.education.util.tools.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 讲师信息-审核
 *
 * @author Yuu
 */
@Component
public class AuthApiLecturerAuditBiz extends BaseBiz {

	@Autowired
	private LecturerAuditDao lecturerAuditDao;
	@Autowired
	private LecturerExtDao lecturerExtDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserExtDao userExtDao;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 讲师信息修改接口
	 * 
	 * @param authLecturerAuditBO
	 * @author Yuu
	 */
	public Result<Integer> update(AuthLecturerAuditBO authLecturerAuditBO) {
		LecturerAudit record = BeanUtil.copyProperties(authLecturerAuditBO, LecturerAudit.class);
			record.setAuditOpinion("");
		int resultNum = lecturerAuditDao.updateById(record);
		if (resultNum > 0) {
			return Result.success(resultNum);
		}
		return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

	/**
	 * 讲师信息查看接口
	 * 
	 * @param authLecturerAuditViewBO
	 * @author Yuu
	 */
	public Result<AuthLecturerAuditViewDTO> view(AuthLecturerAuditViewBO authLecturerAuditViewBO) {
		if (null == authLecturerAuditViewBO.getLecturerUserNo()) {
			return Result.error("讲师编号不能为空");
		}
		LecturerAudit lecturerAudit = lecturerAuditDao.getByLecturerUserNo(authLecturerAuditViewBO.getLecturerUserNo());
		if (lecturerAudit == null) {
			return Result.error("找不到该讲师");
		}
		return Result.success(BeanUtil.copyProperties(lecturerAudit, AuthLecturerAuditViewDTO.class));
	}

	/**
	 * 讲师申请接口
	 *
	 * @param authLecturerAuditSaveBO
	 * @author Yuu
	 */
	@Transactional
	public Result<Integer> save(AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
		if (StringUtils.isEmpty(authLecturerAuditSaveBO.getLecturerMobile())) {
			return Result.error("手机号不能为空");
		}
		// 手机号去空处理
		authLecturerAuditSaveBO.setLecturerMobile(authLecturerAuditSaveBO.getLecturerMobile().trim());
		// 手机号码校验
		if (!Pattern.compile(REGEX_MOBILE).matcher(authLecturerAuditSaveBO.getLecturerMobile()).matches()) {
			return Result.error("手机号码格式不正确");
		}

		// 根据传入的手机号与正常状态获取用户信息
		UserExt userExt = userExtDao.getByMobile(authLecturerAuditSaveBO.getLecturerMobile());
		if (ObjectUtil.isNull(userExt) || StatusIdEnum.NO.getCode().equals(userExt.getStatusId())) {
			// 新用户
			if (StringUtils.isEmpty(authLecturerAuditSaveBO.getCode())) {
				return Result.error("验证码不能为空");
			}
			// 验证码校验
			String redisSmsCode = redisTemplate.opsForValue().get(authLecturerAuditSaveBO.getLecturerMobile());
			if (StringUtils.isEmpty(redisSmsCode)) {
				return Result.error("验证码过期，请重新获取");
			}
			if (!redisSmsCode.equals(authLecturerAuditSaveBO.getCode())) {
				return Result.error("验证码不正确,重新输入");
			}
			// 密码校验
			if (!authLecturerAuditSaveBO.getMobilePsw().equals(authLecturerAuditSaveBO.getRepassword())) {
				return Result.error("密码不一致");
			}
			// 用户不存在，注册用户
			userExt = register(authLecturerAuditSaveBO);
		}

		// 老用户
		LecturerAudit lecturerAudit = lecturerAuditDao.getByLecturerUserNo(userExt.getUserNo());
		if (ObjectUtil.isNotNull(lecturerAudit)) {
			if (AuditStatusEnum.WAIT.getCode().equals(lecturerAudit.getAuditStatus())) {
				return Result.error(ResultEnum.LECTURER_REQUISITION_WAIT.getDesc());
			}
			if (AuditStatusEnum.SUCCESS.getCode().equals(lecturerAudit.getAuditStatus())) {
				return Result.error(ResultEnum.LECTURER_REQUISITION_YET.getDesc());
			}
			if (AuditStatusEnum.FAIL.getCode().equals(lecturerAudit.getAuditStatus())) {
				// 更新讲师审核表为待审核状态
				return Result.success(updateLecturer(lecturerAudit));
			}
		}

		// 添加讲师-只是添加审核表，需要审核
		return Result.success(addLecturer(authLecturerAuditSaveBO, userExt));
	}

	/**
	 * 更新讲师信息
	 */
	private int updateLecturer(LecturerAudit lecturerAudit) {
		lecturerAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
		int lecturerAuditNum = lecturerAuditDao.updateById(lecturerAudit);
		if (lecturerAuditNum < 1) {
			throw new BaseException("更新讲师信息失败");
		}
		return lecturerAuditNum;
	}

	/**
	 * 插入讲师信息
	 */
	private int addLecturer(AuthLecturerAuditSaveBO authLecturerAuditSaveBO, UserExt userExt) {
		// 插入讲师信息
		LecturerAudit infoAudit = BeanUtil.copyProperties(authLecturerAuditSaveBO, LecturerAudit.class);
		if (!StringUtils.isEmpty(userExt.getHeadImgUrl())) {
			infoAudit.setHeadImgUrl(userExt.getHeadImgUrl());
		}
		infoAudit.setLecturerUserNo(userExt.getUserNo());
		infoAudit.setLecturerProportion(LECTURER_DEFAULT_PROPORTION);// 讲师默认分成
		int lecturerAuditNum = lecturerAuditDao.save(infoAudit);
		if (lecturerAuditNum < 1) {
			throw new BaseException("讲师信息表新增失败");
		}

		// 插入讲师账户
		LecturerExt lecturerExt = new LecturerExt();
		lecturerExt.setLecturerUserNo(infoAudit.getLecturerUserNo());
		lecturerExt.setTotalIncome(BigDecimal.ZERO);
		lecturerExt.setHistoryMoney(BigDecimal.ZERO);
		lecturerExt.setEnableBalances(BigDecimal.ZERO);
		lecturerExt.setFreezeBalances(BigDecimal.ZERO);
		lecturerExt.setSign(SignUtil.getByLecturer(lecturerExt.getTotalIncome(), lecturerExt.getHistoryMoney(), lecturerExt.getEnableBalances(), lecturerExt.getFreezeBalances()));
		int lecturerExtNum = lecturerExtDao.save(lecturerExt);
		if (lecturerExtNum < 1) {
			throw new BaseException("讲师账户表新增失败");
		}

		return lecturerExtNum;
	}

	/**
	 * 添加用户信息
	 */
	private UserExt register(AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
		// 用户基本信息
		User user = new User();
		user.setUserNo(NOUtil.getUserNo());
		user.setMobile(authLecturerAuditSaveBO.getLecturerMobile());
		user.setMobileSalt(StrUtil.get32UUID());
		user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + authLecturerAuditSaveBO.getMobilePsw()));
		int userNum = userDao.save(user);
		if (userNum < 1) {
			throw new BaseException("用户信息表新增失败");
		}

		// 用户教育信息
		UserExt userExt = new UserExt();
		userExt.setUserNo(user.getUserNo());
		userExt.setMobile(user.getMobile());
		userExt.setNickname(authLecturerAuditSaveBO.getLecturerName());
		int userExtNum = userExtDao.save(userExt);
		if (userExtNum < 1) {
			throw new BaseException("用户教育信息表新增失败");
		}

		return userExt;
	}

}
