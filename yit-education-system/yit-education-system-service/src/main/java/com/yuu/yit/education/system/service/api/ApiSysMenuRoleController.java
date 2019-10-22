package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiSysMenuRoleBiz;
import com.yuu.yit.education.util.base.BaseController;

/**
 * 菜单角色关联表
 *
 * @author Yuu
 */
@RestController
public class ApiSysMenuRoleController extends BaseController {

	@Autowired
	private ApiSysMenuRoleBiz biz;

}
