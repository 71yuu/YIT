package com.yuu.yit.education.user.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.user.common.bean.qo.PlatformQO;
import com.yuu.yit.education.user.common.bean.vo.PlatformVO;
import com.yuu.yit.education.user.common.interfaces.BossPlatform;
import com.yuu.yit.education.user.service.controller.biz.BossPlatformBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 平台信息
 *
 * @author Yuu
 */
@RestController
public class BossPlatformController extends BaseController implements BossPlatform {

	@Autowired
	private BossPlatformBiz biz;

	@Override
	public Page<PlatformVO> listForPage(@RequestBody PlatformQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody PlatformQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody PlatformQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public PlatformVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public PlatformVO getByClientId(@PathVariable(value = "clientId") String clientId) {
		return biz.getByClientId(clientId);
	}

}
