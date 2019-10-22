package com.yuu.yit.education.course.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.course.service.biz.ApiAdvBiz;
import com.yuu.yit.education.course.service.common.bo.AdvBO;
import com.yuu.yit.education.course.service.common.dto.AdvListDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 广告信息
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/course/api/adv")
public class ApiAdvController extends BaseController {

	@Autowired
	private ApiAdvBiz biz;

	@ApiOperation(value = "广告列表接口", notes = "首页轮播广告列出")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<AdvListDTO> list(@RequestBody AdvBO advBO) {
		return biz.list(advBO);
	}

}
