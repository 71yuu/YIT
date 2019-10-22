package com.yuu.yit.education.system.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.system.common.bean.qo.SysUserQO;
import com.yuu.yit.education.system.common.bean.vo.SysUserVO;
import com.yuu.yit.education.system.service.dao.SysUserDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysUser;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysUserExample;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 后台用户信息
 *
 * @author Yuu
 */
@Component
public class BossSysUserBiz {

	@Autowired
	private SysUserDao dao;

	public Page<SysUserVO> listForPage(SysUserQO qo) {
		SysUserExample example = new SysUserExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<SysUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysUserVO.class);
	}

	public int save(SysUserQO qo) {
		SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysUserVO getById(Long id) {
		SysUser record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysUserVO.class);
	}

	public int updateById(SysUserQO qo) {
		SysUser record = BeanUtil.copyProperties(qo, SysUser.class);
		return dao.updateById(record);
	}

}
