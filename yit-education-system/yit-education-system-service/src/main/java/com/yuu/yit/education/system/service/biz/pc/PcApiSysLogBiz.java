package com.yuu.yit.education.system.service.biz.pc;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.system.service.common.req.SysLogPageREQ;
import com.yuu.yit.education.system.service.common.resq.SysLogPageRESQ;
import com.yuu.yit.education.system.service.dao.SysLogDao;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysLog;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysLogExample;
import com.yuu.yit.education.system.service.dao.impl.mapper.entity.SysLogExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.tools.DateUtil;

/**
 * 后台操作日志表
 *
 * @author Yuu
 */
@Component
public class PcApiSysLogBiz {

	@Autowired
	private SysLogDao sysLogDao;

	public Result<Page<SysLogPageRESQ>> list(SysLogPageREQ req) {
		SysLogExample example = new SysLogExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNotEmpty(req.getRealName())) {
			c.andRealNameLike(PageUtil.like(req.getRealName()));
		}
		if (StringUtils.isNotEmpty(req.getBeginDate())) {
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginDate(), "yyyy-MM-dd"));
		}
		if (StringUtils.isNotEmpty(req.getEndDate())) {
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndDate(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" id desc ");
		Page<SysLog> page = sysLogDao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, SysLogPageRESQ.class));
	}

}
