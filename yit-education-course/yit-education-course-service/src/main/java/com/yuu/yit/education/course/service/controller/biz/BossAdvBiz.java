package com.yuu.yit.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.course.common.bean.qo.AdvQO;
import com.yuu.yit.education.course.common.bean.vo.AdvVO;
import com.yuu.yit.education.course.service.dao.AdvDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Adv;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.AdvExample;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.AdvExample.Criteria;
import com.yuu.yit.education.system.feign.IBossSys;
import com.yuu.yit.education.util.aliyun.Aliyun;
import com.yuu.yit.education.util.aliyun.AliyunUtil;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 广告信息
 *
 * @author Yuu
 */
@Component
public class BossAdvBiz {

	@Autowired
	private AdvDao dao;

	@Autowired
	private IBossSys bossSys;

	public Page<AdvVO> listForPage(AdvQO qo) {
		AdvExample example = new AdvExample();
		Criteria c = example.createCriteria();
		if (qo.getPlatShow() != null) {
			c.andPlatShowEqualTo(qo.getPlatShow());
		}
		if (StringUtils.hasText(qo.getAdvTitle())) {
			c.andAdvTitleEqualTo(qo.getAdvTitle());
		}
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<Adv> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, AdvVO.class);
	}

	public int save(AdvQO qo) {
		Adv record = BeanUtil.copyProperties(qo, Adv.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		Adv adv = dao.getById(id);
		if (adv != null) {
			AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
		}
		return dao.deleteById(id);
	}

	public AdvVO getById(Long id) {
		Adv record = dao.getById(id);
		return BeanUtil.copyProperties(record, AdvVO.class);
	}

	public int updateById(AdvQO qo) {
		Adv adv = dao.getById(qo.getId());
		if (StringUtils.hasText(qo.getAdvImg()) && !adv.getAdvImg().equals(qo.getAdvImg())) {
			AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class));
		}
		Adv record = BeanUtil.copyProperties(qo, Adv.class);
		return dao.updateById(record);
	}

}
