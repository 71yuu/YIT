package com.yuu.yit.education.system.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.pc.PcApiSysRoleUserBiz;
import com.yuu.yit.education.system.service.common.req.SysRoleUserListREQ;
import com.yuu.yit.education.system.service.common.req.SysRoleUserSaveREQ;
import com.yuu.yit.education.system.service.common.resq.SysRoleUserListRESQ;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 角色用户关联表
 *
 * @author Yuu
 */
@RestController
@RequestMapping(value = "/system/pc/sys/role/user")
public class PcApiSysRoleUserController extends BaseController {

	@Autowired
	private PcApiSysRoleUserBiz biz;

	/**
	 * 列出角色用户关联信息接口
	 */
	@ApiOperation(value = "列出角色用户关联信息接口", notes = "列出角色用户关联信息接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<SysRoleUserListRESQ> list(@RequestBody SysRoleUserListREQ sysRoleUserListREQ) {
		return biz.list(sysRoleUserListREQ);
	}

	/**
	 * 添加用户角色信息接口
	 */
	@ApiOperation(value = "添加用户角色信息接口", notes = "添加用户角色信息接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody SysRoleUserSaveREQ sysRoleUserSaveREQ) {
		return biz.save(sysRoleUserSaveREQ);
	}

}
