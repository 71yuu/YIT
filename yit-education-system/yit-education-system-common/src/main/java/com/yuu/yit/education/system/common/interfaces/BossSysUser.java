package com.yuu.yit.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuu.yit.education.system.common.bean.qo.SysUserQO;
import com.yuu.yit.education.system.common.bean.vo.SysUserVO;
import com.yuu.yit.education.util.base.Page;

/**
 * 后台用户信息 
 *
 * @author Yuu
 */
public interface BossSysUser {
	
	@RequestMapping(value = "/boss/system/sysUser/listForPage")
	Page<SysUserVO> listForPage(@RequestBody SysUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysUser/save")
	int save(@RequestBody SysUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysUser/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysUser/updateById")
	int updateById(@RequestBody SysUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysUser/getById")
	SysUserVO getById(@RequestBody Long id);
	
}
