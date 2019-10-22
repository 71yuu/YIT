package com.yuu.yit.education.user.service.biz;

import com.aliyuncs.exceptions.ClientException;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import com.yuu.yit.education.system.common.bean.vo.SysVO;
import com.yuu.yit.education.system.feign.IBossSys;
import com.yuu.yit.education.user.service.common.bo.UserLoginPasswordBO;
import com.yuu.yit.education.user.service.common.bo.UserRegisterBO;
import com.yuu.yit.education.user.service.common.bo.UserSendCodeBO;
import com.yuu.yit.education.user.service.common.bo.auth.UserUpdateBO;
import com.yuu.yit.education.user.service.common.dto.UserLoginDTO;
import com.yuu.yit.education.user.service.dao.*;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.*;
import com.yuu.yit.education.util.aliyun.Aliyun;
import com.yuu.yit.education.util.aliyun.AliyunUtil;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.BaseException;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.*;
import com.yuu.yit.education.util.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 用户基本信息
 *
 * @author Yuu
 */
@Component
public class ApiUserInfoBiz extends BaseBiz {

	@Autowired
	private IBossSys bossSys;

	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserExtDao userExtDao;
	@Autowired
	private SendSmsLogDao sendSmsLogDao;
	@Autowired
	private UserLogLoginDao userLogLoginDao;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Transactional
	public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) {
		if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
			return Result.error("密码不能为空");
		}

		// 密码校验
		if (!userRegisterBO.getPassword().equals(userRegisterBO.getRepassword())) {
			return Result.error("2次密码不一致");
		}

		// 验证码校验
		String redisSmsCode = redisTemplate.opsForValue().get(userRegisterBO.getMobile());
		if (StringUtils.isEmpty(redisSmsCode)) {
			return Result.error("请输入验证码");
		}
		if (!redisSmsCode.equals(userRegisterBO.getCode())) {
			return Result.error("验证码不正确，请重新输入");
		}

		// 手机号重复校验
		User user = userDao.getByMobile(userRegisterBO.getMobile());
		if (null != user) {
			return Result.error("该手机号已经注册，请更换手机号");
		}

		// 用户注册
		user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
		return Result.success(dto);
	}

	public Result<UserLoginDTO> loginPassword(UserLoginPasswordBO userLoginPasswordBO) {
		if (StringUtils.isEmpty(userLoginPasswordBO.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(userLoginPasswordBO.getPassword())) {
			return Result.error("密码不能为空");
		}

		// 查询是否被禁止登录
		String str = redisTemplate.opsForValue().get(RedisPreEnum.BAN_LOGIN.getCode() + userLoginPasswordBO.getMobile());
		if (!StringUtils.isEmpty(str)) {
			return Result.error("您已被禁止登录，请稍后再试！");
		}

		// 用户校验
		User user = userDao.getByMobile(userLoginPasswordBO.getMobile());
		if (null == user) {
			return Result.error("账号或者密码不正确");
		}

		// 查看用户状态
		UserExt userExt = userExtDao.getByUserNo(user.getUserNo());
		if (UserStatusEnum.BAN.getCode().equals(userExt.getStatusId())) {
			return Result.error("您的账号已被封禁，请联系管理员进行解封！");
		}

		// 密码校验
		if (!DigestUtil.sha1Hex(user.getMobileSalt() + userLoginPasswordBO.getPassword()).equals(user.getMobilePsw())) {
			loginLog(user.getUserNo(), LoginStatusEnum.FAIL, userLoginPasswordBO.getIp());
			// 获取密码错误次数
			String errCount = redisTemplate.opsForValue().get(RedisPreEnum.LOGIN_PASS_ERROR.getCode() + userLoginPasswordBO.getMobile());
			Integer errorCount = errCount != null ? Integer.valueOf(errCount) : 0;
			// 如果输入错误密码超过 5 次
			if (errorCount > 5) {
				// 禁止登录 5 分钟
				redisTemplate.opsForValue().set(RedisPreEnum.BAN_LOGIN.getCode() + userLoginPasswordBO.getMobile(), "已被禁止登录", 5, TimeUnit.MINUTES);
				return Result.error("您已输入密码错误 5 次，将被限制于 5 分钟后登录");
			}
			// 放入缓存，错误次数+1
			String countVal = (++errorCount).toString();
			redisTemplate.opsForValue().set(RedisPreEnum.LOGIN_PASS_ERROR.getCode() + userLoginPasswordBO.getMobile(), countVal, 5, TimeUnit.MINUTES);
			return Result.error("账号或者密码不正确");
		}

		// 登录日志
		loginLog(user.getUserNo(), LoginStatusEnum.SUCCESS, userLoginPasswordBO.getIp());

		UserLoginDTO dto = new UserLoginDTO();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		return Result.success(dto);
	}


	public Result<String> sendCode(UserSendCodeBO userSendCodeBO) {
		if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(userSendCodeBO.getMobile()).matches()) {
			return Result.error("手机号码格式不正确");
		}
		// 获取短信发送配置
		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return Result.error("找不到系统配置信息");
		}
		// 创建日志实例
		SendSmsLog sendSmsLog = new SendSmsLog();
		sendSmsLog.setMobile(userSendCodeBO.getMobile());
		sendSmsLog.setTemplate(sys.getSmsCode());
		// 随机生成验证码
		sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
		try {
			// 发送验证码
			boolean result = AliyunUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
			// 发送成功，验证码存入缓存：5分钟有效
			if (result) {
				redisTemplate.opsForValue().set(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
				sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
				sendSmsLogDao.save(sendSmsLog);
				return Result.success("发送成功");
			}
			// 发送失败
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			throw new BaseException("发送失败");
		} catch (ClientException e) {
			sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
			sendSmsLogDao.save(sendSmsLog);
			logger.error("发送失败，原因={}", e.getErrMsg());
			return Result.error("发送失败");
		}
	}

	private User register(String mobile, String password) {
		// 用户基本信息
		User user = new User();
		user.setUserNo(NOUtil.getUserNo());
		user.setMobile(mobile);
		user.setMobileSalt(StrUtil.get32UUID());
		user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
		userDao.save(user);

		// 用户其他信息
		UserExt userExt = new UserExt();
		userExt.setUserNo(user.getUserNo());
		userExt.setUserType(UserTypeEnum.USER.getCode());
		userExt.setMobile(user.getMobile());
		userExtDao.save(userExt);

		return user;
	}

	private void loginLog(Long userNo, LoginStatusEnum status, String ip) {
		UserLogLogin record = new UserLogLogin();
		record.setUserNo(userNo);
		record.setLoginStatus(status.getCode());
		record.setLoginIp(ip);
		userLogLoginDao.save(record);
	}

	public Result<Integer> updatePassword(UserUpdateBO userUpdateBO) {
		if (StringUtils.isEmpty(userUpdateBO.getMobile())) {
			return Result.error("手机号为空,请重试");
		}
		if (StringUtils.isEmpty(userUpdateBO.getCode())) {
			return Result.error("验证码不能为空");
		}

		String redisCode = redisTemplate.opsForValue().get(userUpdateBO.getMobile());
		if (StringUtils.isEmpty(redisCode)) {
			return Result.error("请输入验证码");
		}
		if (!userUpdateBO.getCode().equals(redisCode)) {
			return Result.error("验证码匹配不正确");
		}
		// 手机号去空处理
		String mobile = userUpdateBO.getMobile().replaceAll(" +", "");

		if (StringUtils.isEmpty(userUpdateBO.getConfirmPassword())) {
			return Result.error("新登录密码为空,请重试");
		}
		if (!userUpdateBO.getNewPassword().equals(userUpdateBO.getConfirmPassword())) {
			return Result.error("密码输入不一致，请重试");
		}

		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("没找到用户信息,请重试");
		}
		if (DigestUtil.sha1Hex(user.getMobileSalt() + userUpdateBO.getNewPassword()).equals(user.getMobilePsw())) {
			return Result.error("输入的密码与原密码一致,请重试");
		}

		// 更新密码
		User bean = new User();
		bean.setId(user.getId());
		bean.setMobileSalt(StrUtil.get32UUID());
		bean.setMobilePsw(DigestUtil.sha1Hex(bean.getMobileSalt() + userUpdateBO.getNewPassword()));
		int result = userDao.updateById(bean);
		return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

}
