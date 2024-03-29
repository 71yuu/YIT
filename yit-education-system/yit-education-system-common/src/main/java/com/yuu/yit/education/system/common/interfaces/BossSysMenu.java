package com.yuu.yit.education.system.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuu.yit.education.system.common.bean.qo.SysMenuQO;
import com.yuu.yit.education.system.common.bean.vo.SysMenuVO;
import com.yuu.yit.education.util.base.Page;

/**
 * 菜单信息 
 *
 * @author Yuu
 */
public interface BossSysMenu {
	
	@RequestMapping(value = "/boss/system/sysMenu/listForPage")
	Page<SysMenuVO> listForPage(@RequestBody SysMenuQO qo);
	
	@RequestMapping(value = "/boss/system/sysMenu/save")
	int save(@RequestBody SysMenuQO qo);
	
	@RequestMapping(value = "/boss/system/sysMenu/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysMenu/updateById")
	int updateById(@RequestBody SysMenuQO qo);
	
	@RequestMapping(value = "/boss/system/sysMenu/getById")
	SysMenuVO getById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysMenu/listByUserAndMenu")
	List<SysMenuVO> listByUserAndMenu(@RequestBody Long userNo);
	
}
