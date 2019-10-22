package com.yuu.yit.education.user.service.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.user.service.common.bo.UserRegionCityIdBO;
import com.yuu.yit.education.user.service.common.bo.UserRegionLevelBO;
import com.yuu.yit.education.user.service.common.bo.UserRegionProvinceBO;
import com.yuu.yit.education.user.service.common.dto.RegionDTO;
import com.yuu.yit.education.user.service.common.dto.RegionListDTO;
import com.yuu.yit.education.user.service.dao.RegionDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.Region;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 行政区域表
 *
 * @author Yuu
 */
@Component
public class ApiRegionBiz {

	@Autowired
	private RegionDao regionDao;

	public Result<RegionListDTO> listForLevel(UserRegionLevelBO userRegionLevelBO) {
		List<Region> list = regionDao.listByLevel(userRegionLevelBO.getLevel());
		if (CollectionUtil.isNotEmpty(list)) {
			RegionListDTO data = new RegionListDTO();
			data.setRegionList(PageUtil.copyList(list, RegionDTO.class));
			return Result.success(data);
		}
		return Result.error("找不到信息");
	}

	public Result<RegionListDTO> listForProvince(UserRegionProvinceBO userRegionProvinceBO) {
		List<Region> list = regionDao.listByProvinceId(userRegionProvinceBO.getProvinceId());
		if (CollectionUtil.isNotEmpty(list)) {
			RegionListDTO data = new RegionListDTO();
			data.setRegionList(PageUtil.copyList(list, RegionDTO.class));
			return Result.success(data);
		}
		return Result.error("找不到信息");
	}

	public Result<RegionListDTO> listForCity(UserRegionCityIdBO userRegionCityIdBO) {
		List<Region> list = regionDao.listByCityId(userRegionCityIdBO.getCityId());
		if (CollectionUtil.isNotEmpty(list)) {
			RegionListDTO data = new RegionListDTO();
			data.setRegionList(PageUtil.copyList(list, RegionDTO.class));
			return Result.success(data);
		}
		return Result.error("找不到信息");
	}

}
