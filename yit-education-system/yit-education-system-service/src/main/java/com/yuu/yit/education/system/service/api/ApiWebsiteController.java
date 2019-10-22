package com.yuu.yit.education.system.service.api;

import com.yuu.yit.education.system.service.biz.ApiWebsiteBiz;
import com.yuu.yit.education.system.service.common.dto.WebsiteDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 站点信息
 *
 * @author Yuu
 */
@RestController
public class ApiWebsiteController extends BaseController {

	@Resource
	private ApiWebsiteBiz biz;

	/**
	 * 获取站点信息接口
	 * 
	 * @return 站点信息
	 * @author Yuu
	 */
	@ApiOperation(value = "获取站点信息接口", notes = "获取站点信息")
	@RequestMapping(value = "/system/api/website/get", method = RequestMethod.POST)
	public Result<WebsiteDTO> get() {
		return biz.get();
	}

}
