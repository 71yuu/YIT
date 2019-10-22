package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiSysRoleUserBiz;
import com.yuu.yit.education.util.base.BaseController;

/**
 * 角色用户关联表
 *
 * @author Yuu
 */
@RestController
public class ApiSysRoleUserController extends BaseController {

	@Autowired
	private ApiSysRoleUserBiz biz;

}
