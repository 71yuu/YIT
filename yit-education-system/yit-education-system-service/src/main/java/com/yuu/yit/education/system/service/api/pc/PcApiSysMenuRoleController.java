package com.yuu.yit.education.system.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.pc.PcApiSysMenuRoleBiz;
import com.yuu.yit.education.system.service.common.req.SysMenuRoleListREQ;
import com.yuu.yit.education.system.service.common.req.SysMenuRoleSaveREQ;
import com.yuu.yit.education.system.service.common.resq.SysMenuRoleListRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 菜单角色关联表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/system/pc/menu/role")
public class PcApiSysMenuRoleController extends BaseController {

	@Autowired
	private PcApiSysMenuRoleBiz biz;

	/**
	 * 列出菜单角色关联信息接口
	 */
	@ApiOperation(value = "列出菜单角色关联信息接口", notes = "列出菜单角色关联信息接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<SysMenuRoleListRESQ> list(@RequestBody SysMenuRoleListREQ sysMenuRoleListREQ) {
		return biz.list(sysMenuRoleListREQ);
	}

	/**
	 * 添加角色菜单关联信息接口
	 */
	@ApiOperation(value = "添加角色菜单关联信息接口", notes = "添加角色菜单关联信息接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody SysMenuRoleSaveREQ sysMenuRoleSaveREQ) {
		return biz.save(sysMenuRoleSaveREQ);
	}

}
