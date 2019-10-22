package com.yuu.yit.education.user.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.user.service.biz.pc.PcApiSendSmsLogBiz;
import com.yuu.yit.education.user.service.common.req.SendSmsLogPageREQ;
import com.yuu.yit.education.user.service.common.req.SendSmsLogSendREQ;
import com.yuu.yit.education.user.service.common.resq.SendSmsLogPageRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 短信记录
 */
@RestController
@RequestMapping(value = "/user/pc/send/sms/log")
public class PcApiSendSmsLogController extends BaseController {

	@Autowired
	private PcApiSendSmsLogBiz biz;

	/**
	 * 短信记录分页列表接口
	 * 
	 * @param sendSmsLogPageREQ
	 */
	@ApiOperation(value = "短信记录分页列表接口", notes = "短信记录分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<SendSmsLogPageRESQ>> listForPage(@RequestBody SendSmsLogPageREQ sendSmsLogPageREQ) {
		return biz.listForPage(sendSmsLogPageREQ);
	}

	/**
	 * 发送验证码接口
	 * 
	 * @param sendSmsLogSendREQ
	 */
	@ApiOperation(value = "发送验证码接口", notes = "发送验证码接口")
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public Result<Integer> send(@RequestBody SendSmsLogSendREQ sendSmsLogSendREQ) {
		return biz.send(sendSmsLogSendREQ);
	}

}
