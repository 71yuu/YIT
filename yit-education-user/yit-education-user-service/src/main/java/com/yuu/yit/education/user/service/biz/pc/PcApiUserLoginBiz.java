package com.yuu.yit.education.user.service.biz.pc;

import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.yuu.yit.education.system.common.bean.vo.SysMenuVO;
import com.yuu.yit.education.system.common.interfaces.BossSysMenu;
import com.yuu.yit.education.user.service.common.req.UserLoginPasswordREQ;
import com.yuu.yit.education.user.service.common.resq.UserLoginRESQ;
import com.yuu.yit.education.user.service.dao.UserDao;
import com.yuu.yit.education.user.service.dao.UserLogLoginDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.User;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.LoginStatusEnum;
import com.yuu.yit.education.util.enums.RedisPreEnum;
import com.yuu.yit.education.util.tools.JSONUtil;
import com.yuu.yit.education.util.tools.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class PcApiUserLoginBiz {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserLogLoginDao userLogLoginDao;

	@Autowired
	private BossSysMenu bossSysMenu;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Result<UserLoginRESQ> loginPassword(UserLoginPasswordREQ req) {
		// 手机号不能为空
		if (StringUtils.isEmpty(req.getMobile())) {
			return Result.error("手机号不能为空");
		}

		// 密码
		if (StringUtils.isEmpty(req.getPassword())) {
			return Result.error("密码不能为空");
		}

		// 用户校验
		User user = userDao.getByMobile(req.getMobile());
		if (null == user) {
			return Result.error("手机号或者密码不正确");
		}

		// 密码校验
		if (!DigestUtil.sha1Hex(user.getMobileSalt() + req.getPassword()).equals(user.getMobilePsw())) {
			loginLog(user.getUserNo(), LoginStatusEnum.FAIL, req.getIp());
			return Result.error("账号或者密码不正确");
		}

		// 获取用户菜单权限
		List<SysMenuVO> menuList = bossSysMenu.listByUserAndMenu(user.getUserNo());
		if (CollectionUtil.isEmpty(menuList)) {
			return Result.error("没权限！");
		}

		// 登录日志
		loginLog(user.getUserNo(), LoginStatusEnum.SUCCESS, req.getIp());

		// 用户菜单存入缓存, 时间缓存 1 天
		redisTemplate.opsForValue().set(RedisPreEnum.ADMINI_MENU.getCode().concat(user.getUserNo().toString()), JSONUtil.toJSONString(menuList), 1, TimeUnit.DAYS);

		// 封装用户登录返回对象
		UserLoginRESQ dto = new UserLoginRESQ();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		return Result.success(dto);
	}

	private void loginLog(Long userNo, LoginStatusEnum status, String ip) {
		UserLogLogin record = new UserLogLogin();
		record.setUserNo(userNo);
		record.setLoginStatus(status.getCode());
		record.setLoginIp(ip);
		userLogLoginDao.save(record);
	}
}
