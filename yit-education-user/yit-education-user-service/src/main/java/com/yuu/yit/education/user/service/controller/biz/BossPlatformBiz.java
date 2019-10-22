package com.yuu.yit.education.user.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.user.common.bean.qo.PlatformQO;
import com.yuu.yit.education.user.common.bean.vo.PlatformVO;
import com.yuu.yit.education.user.service.dao.PlatformDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.Platform;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.PlatformExample;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.PlatformExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 平台信息
 *
 * @author Yuu
 */
@Component
public class BossPlatformBiz {

	@Autowired
	private PlatformDao dao;

	public Page<PlatformVO> listForPage(PlatformQO qo) {
		PlatformExample example = new PlatformExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(qo.getClientName())) {
			c.andClientNameLike(PageUtil.rightLike(qo.getClientName()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Platform> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, PlatformVO.class);
	}

	public int save(PlatformQO qo) {
		Platform record = BeanUtil.copyProperties(qo, Platform.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public PlatformVO getById(Long id) {
		Platform record = dao.getById(id);
		return BeanUtil.copyProperties(record, PlatformVO.class);
	}

	public int updateById(PlatformQO qo) {
		Platform record = BeanUtil.copyProperties(qo, Platform.class);
		return dao.updateById(record);
	}

	public PlatformVO getByClientId(String clientId) {
		Platform record = dao.getByClientId(clientId);
		return BeanUtil.copyProperties(record, PlatformVO.class);
	}

}
