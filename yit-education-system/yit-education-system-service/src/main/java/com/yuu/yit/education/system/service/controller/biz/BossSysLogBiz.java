package com.yuu.yit.education.system.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.system.common.bean.qo.SysLogQO;
import com.yuu.yit.education.system.common.bean.vo.SysLogVO;
import com.yuu.yit.education.system.service.dao.SysLogDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysLog;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysLogExample;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysLogExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 后台操作日志表
 *
 * @author Yuu
 */
@Component
public class BossSysLogBiz {

	@Autowired
	private SysLogDao dao;

	public Page<SysLogVO> listForPage(SysLogQO qo) {
		SysLogExample example = new SysLogExample();
		Criteria c = example.createCriteria();
		example.setOrderByClause(" id desc ");
		Page<SysLog> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, SysLogVO.class);
	}

	public int save(SysLogQO qo) {
		SysLog record = BeanUtil.copyProperties(qo, SysLog.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public SysLogVO getById(Long id) {
		SysLog record = dao.getById(id);
		return BeanUtil.copyProperties(record, SysLogVO.class);
	}

	public int updateById(SysLogQO qo) {
		SysLog record = BeanUtil.copyProperties(qo, SysLog.class);
		return dao.updateById(record);
	}

}
