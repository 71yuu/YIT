package com.yuu.yit.education.system.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.auth.AuthApiMsgBiz;
import com.yuu.yit.education.system.service.common.bo.MsgViewBO;
import com.yuu.yit.education.system.service.common.dto.MsgDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站内信息表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/system/auth/msg")
public class AuthApiMsg extends BaseController {

	@Autowired
	private AuthApiMsgBiz biz;

	/**
	 * 课站内信详情
	 */
	@ApiOperation(value = "站内信详情", notes = "站内信详情接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	Result<MsgDTO> view(@RequestBody MsgViewBO MsgViewBO) {
		return biz.view(MsgViewBO);
	};
}
