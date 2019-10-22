package com.yuu.yit.education.system.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.pc.PcApiSysLogBiz;
import com.yuu.yit.education.system.service.common.req.SysLogPageREQ;
import com.yuu.yit.education.system.service.common.resq.SysLogPageRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 后台操作日志表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/system/pc/sys/log")
public class PcApiSysLogController extends BaseController {

	@Autowired
	private PcApiSysLogBiz biz;

	/**
	 * 后台操作日志分页列表接口
	 */
	@ApiOperation(value = "后台操作日志分页列表接口", notes = "后台操作日志分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<SysLogPageRESQ>> list(@RequestBody SysLogPageREQ sysLogPageREQ) {
		return biz.list(sysLogPageREQ);
	}

}
