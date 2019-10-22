package com.yuu.yit.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.system.service.biz.ApiSysMenuBiz;
import com.yuu.yit.education.util.base.BaseController;

/**
 * 菜单信息
 *
 * @author Yuu
 */
@RestController
public class ApiSysMenuController extends BaseController {

	@Autowired
	private ApiSysMenuBiz biz;

}
