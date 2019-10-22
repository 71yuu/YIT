package com.yuu.yit.education.system.service.dao.impl;

import com.yuu.yit.education.system.service.dao.NavBarDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.NavBarMapper;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBar;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBarExample;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.NavBarExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NavBarDaoImpl implements NavBarDao {
	@Autowired
	private NavBarMapper navBarMapper;

	public int save(NavBar record) {
		record.setId(IdWorker.getId());
		return this.navBarMapper.insertSelective(record);
	}

	public int deleteById(Long id) {
		return this.navBarMapper.deleteByPrimaryKey(id);
	}

	public int updateById(NavBar record) {
		return this.navBarMapper.updateByPrimaryKeySelective(record);
	}

	public NavBar getById(Long id) {
		return this.navBarMapper.selectByPrimaryKey(id);
	}

	public Page<NavBar> listForPage(int pageCurrent, int pageSize, NavBarExample example) {
		// 获取总数
		int count = this.navBarMapper.countByExample(example);
		// 获取每页大小数量
		pageSize = PageUtil.checkPageSize(pageSize);
		// 获取当前页数
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		// 获取总页数
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		// 设置分页
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		// 设置分页大小
		example.setPageSize(pageSize);
		return new Page<NavBar>(count, totalPage, pageCurrent, pageSize, this.navBarMapper.selectByExample(example));
	}

	@Override
	public NavBar getByNavUrl(String navUrl) {
		NavBarExample example = new NavBarExample();
		Criteria criteria = example.createCriteria();
		criteria.andNavUrlEqualTo(navUrl);
		List<NavBar> list = this.navBarMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<NavBar> getByStatusId(Integer statusId) {
		NavBarExample example = new NavBarExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusIdEqualTo(statusId);
		example.setOrderByClause("sort desc, id desc ");
		return this.navBarMapper.selectByExample(example);
	}
}