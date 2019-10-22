package com.yuu.yit.education.system.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.system.common.bean.qo.SysMenuRoleQO;
import com.yuu.yit.education.system.common.bean.vo.SysMenuRoleVO;
import com.yuu.yit.education.system.service.dao.SysMenuRoleDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysMenuRole;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysMenuRoleExample;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysMenuRoleExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 菜单角色关联表
 *
 * @author Yuu
 */
@Component
public class BossSysMenuRoleBiz {

	@Autowired
	private SysMenuRoleDao dao;

	public Page<SysMenuRoleVO> listForPage(SysMenuRoleQO qo) {
		SysMenuRoleExample example = new SysMenuRoleExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<SysMenuRole> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysMenuRoleVO.class);
	}

	public int save(SysMenuRoleQO qo) {
		SysMenuRole record = BeanUtil.copyProperties(qo, SysMenuRole.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysMenuRoleVO getById(Long id) {
		SysMenuRole record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysMenuRoleVO.class);
	}

	public int updateById(SysMenuRoleQO qo) {
		SysMenuRole record = BeanUtil.copyProperties(qo, SysMenuRole.class);
		return dao.updateById(record);
	}

}