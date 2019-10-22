package com.yuu.yit.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuu.yit.education.system.common.bean.qo.SysRoleQO;
import com.yuu.yit.education.system.common.bean.vo.SysRoleVO;
import com.yuu.yit.education.util.base.Page;

/**
 * 角色信息 
 *
 * @author Yuu
 */
public interface BossSysRole {
	
	@RequestMapping(value = "/boss/system/sysRole/listForPage")
	Page<SysRoleVO> listForPage(@RequestBody SysRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysRole/save")
	int save(@RequestBody SysRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysRole/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysRole/updateById")
	int updateById(@RequestBody SysRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysRole/getById")
	SysRoleVO getById(@RequestBody Long id);
	
}
