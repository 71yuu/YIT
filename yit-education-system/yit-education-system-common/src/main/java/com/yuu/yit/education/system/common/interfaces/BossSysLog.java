package com.yuu.yit.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuu.yit.education.system.common.bean.qo.SysLogQO;
import com.yuu.yit.education.system.common.bean.vo.SysLogVO;
import com.yuu.yit.education.util.base.Page;

/**
 * 后台操作日志表 
 *
 * @author Yuu
 */
public interface BossSysLog {
	
	@RequestMapping(value = "/boss/system/sysLog/listForPage")
	Page<SysLogVO> listForPage(@RequestBody SysLogQO qo);
	
	@RequestMapping(value = "/boss/system/sysLog/save")
	int save(@RequestBody SysLogQO qo);
	
	@RequestMapping(value = "/boss/system/sysLog/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysLog/updateById")
	int updateById(@RequestBody SysLogQO qo);
	
	@RequestMapping(value = "/boss/system/sysLog/getById")
	SysLogVO getById(@RequestBody Long id);
	
}
