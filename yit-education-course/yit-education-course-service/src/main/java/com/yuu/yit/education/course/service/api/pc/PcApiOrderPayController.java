package com.yuu.yit.education.course.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.course.service.biz.pc.PcApiOrderPayBiz;
import com.yuu.yit.education.course.service.common.req.OrderPayPageREQ;
import com.yuu.yit.education.course.service.common.resq.OrderPayPageRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 支付记录
 */
@RestController
@RequestMapping(value = "/course/pc/order/pay")
public class PcApiOrderPayController extends BaseController {

	@Autowired
	private PcApiOrderPayBiz biz;

	/**
	 * 分页列出支付记录
	 * 
	 * @return
	 */
	@ApiOperation(value = "支付记录分页列表接口", notes = "支付记录分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<OrderPayPageRESQ>> list(@RequestBody OrderPayPageREQ orderPayPageREQ) {
		return biz.list(orderPayPageREQ);
	}

}
