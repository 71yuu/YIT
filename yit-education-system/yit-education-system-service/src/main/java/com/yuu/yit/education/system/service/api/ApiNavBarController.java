package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiNavBarBiz;
import com.yuu.yit.education.system.service.common.dto.NavBarListDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 头部导航
 *
 * @author Yuu
 */
@RestController
public class ApiNavBarController extends BaseController {

	@Autowired
	private ApiNavBarBiz biz;

	/**
	 * 获取头部导航信息接口
	 * 
	 * @return 站点信息
	 * @author Yuu
	 */
	@ApiOperation(value = "获取头部导航信息接口", notes = "获取头部导航信息")
	@RequestMapping(value = "/system/api/nav/bar/list", method = RequestMethod.POST)
	public Result<NavBarListDTO> list() {
		return biz.list();
	}

}
