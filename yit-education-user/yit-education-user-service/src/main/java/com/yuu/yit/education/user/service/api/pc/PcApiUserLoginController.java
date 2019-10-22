package com.yuu.yit.education.user.service.api.pc;

import com.yuu.yit.education.util.tools.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.user.service.biz.pc.PcApiUserLoginBiz;
import com.yuu.yit.education.user.service.common.req.UserLoginPasswordREQ;
import com.yuu.yit.education.user.service.common.resq.UserLoginRESQ;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台用户接口
 */
@RestController
@RequestMapping(value = "/user/pc/api/user/login")
public class PcApiUserLoginController {

	@Autowired
	private PcApiUserLoginBiz biz;

	/**
	 * 用户密码登录接口
	 */
	@ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public Result<UserLoginRESQ> loginPassword(@RequestBody UserLoginPasswordREQ userLoginPasswordREQ, HttpServletRequest request) {
		// 获取登录 IP 地址
		userLoginPasswordREQ.setIp(IPUtil.getIP(request));
		return biz.loginPassword(userLoginPasswordREQ);
	}

}
