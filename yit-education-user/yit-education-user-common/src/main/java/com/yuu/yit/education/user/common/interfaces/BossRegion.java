package com.yuu.yit.education.user.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yuu.yit.education.user.common.bean.qo.RegionQO;
import com.yuu.yit.education.user.common.bean.vo.RegionVO;
import com.yuu.yit.education.util.base.Page;

/**
 * 行政区域表 
 *
 * @author Yuu
 */
public interface BossRegion {
	
	@RequestMapping(value = "/boss/user/region/listForPage", method = RequestMethod.POST)
	Page<RegionVO> listForPage(@RequestBody RegionQO qo);
	
	@RequestMapping(value = "/boss/user/region/save", method = RequestMethod.POST)
	int save(@RequestBody RegionQO qo);
	
	@RequestMapping(value = "/boss/user/region/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/user/region/update", method = RequestMethod.PUT)
	int updateById(@RequestBody RegionQO qo);
	
	@RequestMapping(value = "/boss/user/region/get/{id}", method = RequestMethod.GET)
	RegionVO getById(@PathVariable(value = "id") Long id);
	
}
