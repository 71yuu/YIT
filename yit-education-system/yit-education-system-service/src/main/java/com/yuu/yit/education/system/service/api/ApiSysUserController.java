package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiSysUserBiz;
import com.yuu.yit.education.util.base.BaseController;

/**
 * 后台用户信息
 *
 * @author Yuu
 */
@RestController
public class ApiSysUserController extends BaseController {

	@Autowired
	private ApiSysUserBiz biz;

}
