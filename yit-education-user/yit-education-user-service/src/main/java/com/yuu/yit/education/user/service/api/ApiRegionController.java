package com.yuu.yit.education.user.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.user.service.biz.ApiRegionBiz;
import com.yuu.yit.education.user.service.common.bo.UserRegionCityIdBO;
import com.yuu.yit.education.user.service.common.bo.UserRegionLevelBO;
import com.yuu.yit.education.user.service.common.bo.UserRegionProvinceBO;
import com.yuu.yit.education.user.service.common.dto.RegionListDTO;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 行政区域表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/user/api/region/")
public class ApiRegionController extends BaseController {

	@Autowired
	private ApiRegionBiz biz;

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据级别获取区域列出信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<RegionListDTO> listForLevel(@RequestBody UserRegionLevelBO userRegionLevelBO) {
		return biz.listForLevel(userRegionLevelBO);
	}

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据provinceId获取区域列表信息")
	@RequestMapping(value = "/list/province", method = RequestMethod.POST)
	public Result<RegionListDTO> listForProvince(@RequestBody UserRegionProvinceBO userRegionProvinceBO) {
		return biz.listForProvince(userRegionProvinceBO);
	}

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据cityId获取区域列表信息")
	@RequestMapping(value = "/list/city", method = RequestMethod.POST)
	public Result<RegionListDTO> listForCity(@RequestBody UserRegionCityIdBO userRegionCityIdBO) {
		return biz.listForCity(userRegionCityIdBO);
	}

}
