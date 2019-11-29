package com.yuu.yit.education.user.service.api.auth;

import com.yuu.yit.education.user.service.biz.auth.AuthApiLecturerProfitBiz;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerProfitPageBO;
import com.yuu.yit.education.user.service.common.bo.auth.AuthLecturerProfitSaveBO;
import com.yuu.yit.education.user.service.common.dto.auth.AuthLecturerProfitPageDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 讲师提现日志表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer/profit")
public class AuthApiLecturerProfitController extends BaseController {

	@Autowired
	private AuthApiLecturerProfitBiz biz;

	/**
	 * 讲师提现记录分页列出接口
	 * 
	 * @param authLecturerProfitPageBO
	 * @author Yuu
	 */
	@ApiOperation(value = "讲师提现记录分页列出", notes = "讲师提现记录分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthLecturerProfitPageDTO>> list(@RequestBody AuthLecturerProfitPageBO authLecturerProfitPageBO) {
		return biz.list(authLecturerProfitPageBO);
	}

	/**
	 * 讲师申请提现接口
	 * 
	 * @param authLecturerProfitSaveBO
	 * @author Yuu
	 */
	@ApiOperation(value = "讲师申请提现", notes = "讲师申请提现接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody AuthLecturerProfitSaveBO authLecturerProfitSaveBO) {
		return biz.save(authLecturerProfitSaveBO);
	}

	/**
	 * 讲师可提现余额查询接口
	 *
	 * @return
	 */
	@ApiOperation(value = "讲师可提现余额", notes = "讲师可提现余额")
	@RequestMapping(value = "/balances/enable")
	public Result<BigDecimal> enableBalances(@RequestBody AuthLecturerProfitPageBO authLecturerProfitPageBO) {
		return biz.enableBalances(authLecturerProfitPageBO.getLecturerUserNo());
	}

}
