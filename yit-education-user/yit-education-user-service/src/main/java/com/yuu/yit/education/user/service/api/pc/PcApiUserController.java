package com.yuu.yit.education.user.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.user.service.biz.pc.PcApiUserBiz;
import com.yuu.yit.education.user.service.common.req.UserPageREQ;
import com.yuu.yit.education.user.service.common.resq.UserPageRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 用户信息
 *
 */
@RestController
@RequestMapping(value = "/user/pc/user")
public class PcApiUserController extends BaseController {

	@Autowired
	private PcApiUserBiz biz;

	/**
	 * 用戶分页列出接口
	 */
	@ApiOperation(value = "用戶分页列出接口", notes = "用戶分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<UserPageRESQ>> listForPage(@RequestBody UserPageREQ userPageREQ) {
		return biz.listForPage(userPageREQ);
	}
}
