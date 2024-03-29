package com.yuu.yit.education.system.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.auth.AuthApiMsgUserBiz;
import com.yuu.yit.education.system.service.common.bo.MsgReadBO;
import com.yuu.yit.education.system.service.common.bo.MsgUserBO;
import com.yuu.yit.education.system.service.common.bo.MsgViewBO;
import com.yuu.yit.education.system.service.common.dto.MsgDTO;
import com.yuu.yit.education.system.service.common.dto.MsgReadDTO;
import com.yuu.yit.education.system.service.common.dto.MsgUserDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站内信用户记录表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/system/auth/msg/user")
public class AuthApiMsgUser extends BaseController {

	@Autowired
	private AuthApiMsgUserBiz biz;

	/**
	 * 站内信分页列表接口
	 * 
	 * @param msgUserBO
	 * @return
	 * @author Yuu
	 */
	@ApiOperation(value = "学员站内信分页列表接口", notes = "分页列出学员站内信信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	Result<Page<MsgUserDTO>> list(@RequestBody MsgUserBO msgUserBO) {
		return biz.list(msgUserBO);
	}

	/**
	 * 用户查看站内信内容
	 * 
	 * @param MsgViewBO
	 * @return
	 * @author Yuu
	 */
	@ApiOperation(value = "用户查看站内信", notes = "用户查看站内信")
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	Result<MsgDTO> readMsg(@RequestBody MsgViewBO MsgViewBO) {
		return biz.readMsg(MsgViewBO);
	}

	/**
	 * 用户站内信未读条数
	 * 
	 * @param msgReadBO
	 * @return
	 * @author Yuu
	 */
	@ApiOperation(value = "用户站内信未读条数", notes = "用户站内信未读条数")
	@RequestMapping(value = "/num", method = RequestMethod.POST)
	Result<MsgReadDTO> getNumOfUnReadMsg(@RequestBody MsgReadBO msgReadBO) {
		return biz.getNumOfUnReadMsg(msgReadBO);
	}

}
