package com.yuu.yit.education.user.service.api;

import com.yuu.yit.education.user.service.biz.ApiUserInfoBiz;
import com.yuu.yit.education.user.service.common.bo.UserAuthBO;
import com.yuu.yit.education.user.service.common.bo.UserLoginPasswordBO;
import com.yuu.yit.education.user.service.common.bo.UserRegisterBO;
import com.yuu.yit.education.user.service.common.bo.UserSendCodeBO;
import com.yuu.yit.education.user.service.common.bo.auth.UserUpdateBO;
import com.yuu.yit.education.user.service.common.dto.UserLoginDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.tools.IPUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户基本信息
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/user/api/user")
public class ApiUserInfoController extends BaseController {

	@Autowired
	private ApiUserInfoBiz biz;

	/**
	 * 注册接口
	 */
	@ApiOperation(value = "注册接口", notes = "注册成功返回登录信息")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Result<UserLoginDTO> register(@RequestBody UserRegisterBO userRegisterBO) {
		return biz.register(userRegisterBO);
	}

	/**
	 * 用户密码登录接口
	 */
	@ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
	@RequestMapping(value = "/login/password", method = RequestMethod.POST)
	public Result<UserLoginDTO> loginPassword(@RequestBody UserLoginPasswordBO userLoginPasswordBO, HttpServletRequest request) {
		userLoginPasswordBO.setIp(IPUtil.getIP(request));
		return biz.loginPassword(userLoginPasswordBO);
	}

	/**
	 * 验证码发送接口
	 */
	@ApiOperation(value = "验证码发送接口", notes = "发送手机验证码")
	@RequestMapping(value = "/send/code", method = RequestMethod.POST)
	public Result<String> sendCode(@RequestBody UserSendCodeBO userSendCodeBO) {
		return biz.sendCode(userSendCodeBO);
	}

	/**
	 * 授权登录接口
	 */
	@ApiOperation(value = "授权登录接口", notes = "后台登录到讲师中心时使用")
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public Result<String> auth(@RequestBody UserAuthBO userAuthBO) {
		return null;
	}

	/**
	 * 用户修改密码接口
	 * 
	 * @author Yuu
	 */
	@ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
	@RequestMapping(value = "/update/password", method = RequestMethod.POST)
	public Result<Integer> updatePassword(@RequestBody UserUpdateBO userUpdateBO) {
		return biz.updatePassword(userUpdateBO);
	}

}
