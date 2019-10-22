package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiSysRoleBiz;
import com.yuu.yit.education.util.base.BaseController;

/**
 * 角色信息
 *
 * @author Yuu
 */
@RestController
public class ApiSysRoleController extends BaseController {

	@Autowired
	private ApiSysRoleBiz biz;

}
