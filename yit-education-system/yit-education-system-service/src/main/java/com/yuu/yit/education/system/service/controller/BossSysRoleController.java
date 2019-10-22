package com.yuu.yit.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.yuu.yit.education.system.common.bean.qo.SysRoleQO;
import com.yuu.yit.education.system.common.bean.vo.SysRoleVO;
import com.yuu.yit.education.system.common.interfaces.BossSysRole;
import com.yuu.yit.education.system.service.controller.biz.BossSysRoleBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 角色信息 
 *
 * @author Yuu
 */
@RestController
public class BossSysRoleController extends BaseController implements BossSysRole{

	@Autowired
	private BossSysRoleBiz biz;
	
	@Override
	public Page<SysRoleVO> listForPage(@RequestBody SysRoleQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody SysRoleQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody SysRoleQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public SysRoleVO getById(@RequestBody Long id){
		return biz.getById(id);
	}
	
}
