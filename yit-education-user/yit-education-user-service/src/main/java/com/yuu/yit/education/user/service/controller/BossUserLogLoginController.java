package com.yuu.yit.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.yuu.yit.education.user.common.bean.qo.UserLogLoginQO;
import com.yuu.yit.education.user.common.bean.vo.UserLogLoginVO;
import com.yuu.yit.education.user.common.interfaces.BossUserLogLogin;
import com.yuu.yit.education.user.service.controller.biz.BossUserLogLoginBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 用户错误登录日志 
 *
 * @author Yuu
 */
@RestController
public class BossUserLogLoginController extends BaseController implements BossUserLogLogin{

	@Autowired
	private BossUserLogLoginBiz biz;
	
	@Override
	public Page<UserLogLoginVO> listForPage(@RequestBody UserLogLoginQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody UserLogLoginQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody UserLogLoginQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public UserLogLoginVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
