package com.yuu.yit.education.course.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.abel533.echarts.Option;
import com.yuu.yit.education.course.service.biz.auth.AuthApiOrderInfoBiz;
import com.yuu.yit.education.course.service.common.bo.OrderInfoCloseBO;
import com.yuu.yit.education.course.service.common.bo.auth.AuthOrderInfoContinuePayBO;
import com.yuu.yit.education.course.service.common.bo.auth.AuthOrderInfoForChartsBO;
import com.yuu.yit.education.course.service.common.bo.auth.AuthOrderInfoListBO;
import com.yuu.yit.education.course.service.common.bo.auth.AuthOrderInfoViewBO;
import com.yuu.yit.education.course.service.common.bo.auth.AuthOrderPayBO;
import com.yuu.yit.education.course.service.common.dto.auth.AuthOrderInfoDTO;
import com.yuu.yit.education.course.service.common.dto.auth.AuthOrderInfoListDTO;
import com.yuu.yit.education.course.service.common.dto.auth.AuthOrderInfoListForLecturerDTO;
import com.yuu.yit.education.course.service.common.dto.auth.AuthOrderPayDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 订单信息表
 *
 * @author YZJ
 */
@RestController
@RequestMapping(value = "/course/auth/order/info")
public class AuthApiOrderInfoController extends BaseController {

	@Autowired
	private AuthApiOrderInfoBiz biz;

	/**
	 * 订单列表接口
	 */
	@ApiOperation(value = "订单列表接口", notes = "根据条件分页列出订单信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthOrderInfoListDTO>> listForPage(@RequestBody AuthOrderInfoListBO authOrderInfoListBO) {
		return biz.listForPage(authOrderInfoListBO);
	}

	/**
	 * 课程下单接口
	 */
	@ApiOperation(value = "课程下单接口", notes = "用户购买课程下单接口")
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public Result<AuthOrderPayDTO> pay(@RequestBody AuthOrderPayBO authOrderPayBO) {
		return biz.pay(authOrderPayBO);
	}

	/**
	 * 订单继续支付接口
	 */
	@ApiOperation(value = "订单继续支付接口", notes = "订单继续支付接口")
	@RequestMapping(value = "/continue/pay", method = RequestMethod.POST)
	public Result<AuthOrderPayDTO> continuePay(@RequestBody AuthOrderInfoContinuePayBO authOrderInfoContinuePayBO) {
		return biz.continuePay(authOrderInfoContinuePayBO);
	}

	/**
	 * 关闭订单接口
	 */
	@ApiOperation(value = "关闭订单接口", notes = "关闭订单接口")
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public Result<String> close(@RequestBody OrderInfoCloseBO orderInfoCloseBO) {
		return biz.close(orderInfoCloseBO);
	}

	/**
	 * 订单详情信息接口
	 * 
	 * @param infoBO
	 * @return
	 */
	@ApiOperation(value = "订单详情信息接口", notes = "订单详情信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthOrderInfoDTO> view(@RequestBody AuthOrderInfoViewBO authOrderInfoViewBO) {
		return biz.view(authOrderInfoViewBO);
	}

	/**
	 * 讲师订单收益列表
	 *
	 * @param infoBO
	 * @return
	 */
	@ApiOperation(value = "讲师订单收益列表接口", notes = "讲师订单收益列表接口")
	@RequestMapping(value = "/lecturer", method = RequestMethod.POST)
	public Result<Page<AuthOrderInfoListForLecturerDTO>> list(@RequestBody AuthOrderInfoListBO authOrderInfoListBO) {
		return biz.list(authOrderInfoListBO);
	}

	/**
	 * 讲师收益折线图
	 * 
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "讲师收益折线图", notes = "讲师收益折线图")
	@RequestMapping(value = "/charts", method = RequestMethod.POST)
	public Result<Option> charts(@RequestBody AuthOrderInfoForChartsBO authOrderInfoForChartsBO) {
		return biz.charts(authOrderInfoForChartsBO);
	}

}
