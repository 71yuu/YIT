package com.yuu.yit.education.course.service.biz.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.service.common.req.OrderPayPageREQ;
import com.yuu.yit.education.course.service.common.resq.OrderPayPageRESQ;
import com.yuu.yit.education.course.service.dao.OrderPayDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderPayExample;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderPayExample.Criteria;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;

/**
 * 支付记录
 */
@Component
public class PcApiOrderPayBiz {

	@Autowired
	private OrderPayDao dao;

	public Result<Page<OrderPayPageRESQ>> list(OrderPayPageREQ req) {
		OrderPayExample example = new OrderPayExample();
		Criteria c = example.createCriteria();
		if (req.getOrderNo() != null) {
			c.andOrderNoEqualTo(req.getOrderNo());
		}
		example.setOrderByClause(" id desc ");
		Page<OrderPay> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, OrderPayPageRESQ.class));
	}

}
