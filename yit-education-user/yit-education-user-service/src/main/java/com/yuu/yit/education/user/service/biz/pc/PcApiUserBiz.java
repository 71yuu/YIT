package com.yuu.yit.education.user.service.biz.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.user.service.common.req.UserPageREQ;
import com.yuu.yit.education.user.service.common.resq.UserPageRESQ;
import com.yuu.yit.education.user.service.dao.UserDao;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.User;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.UserExample;
import com.yuu.yit.education.user.service.dao.impl.mapper.entity.UserExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;

/**
 * 用户信息
 */
@Component
public class PcApiUserBiz {

	@Autowired
	private UserDao dao;

	/**
	 * 用戶分页列出
	 * 
	 * @param userPageREQ
	 * @return
	 */
	public Result<Page<UserPageRESQ>> listForPage(UserPageREQ req) {
		UserExample example = new UserExample();
		Criteria c = example.createCriteria();
		if (StringUtils.hasText(req.getMobile())) {
			c.andMobileLike(PageUtil.like(req.getMobile()));
		}
		example.setOrderByClause(" status_id desc, id desc ");
		Page<User> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, UserPageRESQ.class));
	}
}
