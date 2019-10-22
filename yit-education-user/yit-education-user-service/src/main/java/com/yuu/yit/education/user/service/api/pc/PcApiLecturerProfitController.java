package com.yuu.yit.education.user.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.user.service.biz.pc.PcApiLecturerProfitBiz;
import com.yuu.yit.education.user.service.common.req.LecturerProfitBatchREQ;
import com.yuu.yit.education.user.service.common.req.LecturerProfitPageREQ;
import com.yuu.yit.education.user.service.common.req.LecturerProfitUpdateREQ;
import com.yuu.yit.education.user.service.common.resq.LecturerProfitPageRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * 讲师提现日志
 *
 */
@RestController
@RequestMapping(value = "/user/pc/lecturer/profit")
public class PcApiLecturerProfitController extends BaseController {

	@Autowired
	private PcApiLecturerProfitBiz biz;

	/**
	 * 讲师提现分页列表接口
	 * 
	 * @param lecturerProfitPageREQ
	 */
	@ApiOperation(value = "讲师提现分页列表接口", notes = "讲师提现分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<LecturerProfitPageRESQ>> listForPage(@RequestBody LecturerProfitPageREQ lecturerProfitPageREQ) {
		return biz.listForPage(lecturerProfitPageREQ);
	}

	/**
	 * 讲师提现更新提现状态接口
	 * 
	 * @param LecturerProfitUpdateREQ
	 */
	@ApiOperation(value = "讲师提现更新提现状态接口", notes = "讲师提现更新提现状态接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody LecturerProfitUpdateREQ LecturerProfitUpdateREQ) {
		return biz.update(LecturerProfitUpdateREQ);
	}

	/**
	 * 讲师提现批量更新提现状态接口
	 * 
	 * @param lecturerProfitBatchREQ
	 */
	@ApiOperation(value = "讲师提现批量更新提现状态接口", notes = "讲师提现批量更新提现状态接口")
	@RequestMapping(value = "/batch", method = RequestMethod.POST)
	public Result<Integer> batch(@RequestBody LecturerProfitBatchREQ lecturerProfitBatchREQ) {
		return biz.batch(lecturerProfitBatchREQ);
	}

}
