package com.yuu.yit.education.user.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.user.common.bean.qo.RegionQO;
import com.yuu.yit.education.user.common.bean.vo.RegionVO;
import com.yuu.yit.education.user.service.dao.RegionDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.Region;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.RegionExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 行政区域表 
 *
 * @author Yuu
 */
@Component
public class BossRegionBiz {

	@Autowired
	private RegionDao dao;

	public Page<RegionVO> listForPage(RegionQO qo) {
	    RegionExample example = new RegionExample();
	    example.setOrderByClause(" id desc ");
        Page<Region> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, RegionVO.class);
	}

	public int save(RegionQO qo) {
	    Region record = BeanUtil.copyProperties(qo, Region.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public RegionVO getById(Long id) {
	    Region record = dao.getById(id);
		return BeanUtil.copyProperties(record, RegionVO.class);
	}

	public int updateById(RegionQO qo) {
	    Region record = BeanUtil.copyProperties(qo, Region.class);
		return dao.updateById(record);
	}
	
}
