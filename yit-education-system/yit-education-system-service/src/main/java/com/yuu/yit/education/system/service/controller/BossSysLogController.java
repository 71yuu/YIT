package com.yuu.yit.education.system.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.yuu.yit.education.system.common.bean.qo.SysLogQO;
import com.yuu.yit.education.system.common.bean.vo.SysLogVO;
import com.yuu.yit.education.system.common.interfaces.BossSysLog;
import com.yuu.yit.education.system.service.controller.biz.BossSysLogBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 后台操作日志表 
 *
 * @author Yuu
 */
@RestController
public class BossSysLogController extends BaseController implements BossSysLog{

	@Autowired
	private BossSysLogBiz biz;
	
	@Override
	public Page<SysLogVO> listForPage(@RequestBody SysLogQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody SysLogQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@RequestBody Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody SysLogQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public SysLogVO getById(@RequestBody Long id){
		return biz.getById(id);
	}
	
}
