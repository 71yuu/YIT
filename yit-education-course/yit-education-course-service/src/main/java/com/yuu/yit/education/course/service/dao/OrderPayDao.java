package com.yuu.yit.education.course.service.dao;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderPay;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.OrderPayExample;
import com.yuu.yit.education.util.base.Page;

public interface OrderPayDao {
    int save(OrderPay record);

    int deleteById(Long id);

    int updateById(OrderPay record);

    OrderPay getById(Long id);

    Page<OrderPay> listForPage(int pageCurrent, int pageSize, OrderPayExample example);

    /**
	 * 根据订单编号查找订单支付信息
	 * 
	 * @param orderNo
	 * @return
	 */
	OrderPay getByOrderNo(long orderNo);

	/**
	 * 根据流水编号查找订单支付信息
	 * 
	 * @param serialNumber
	 * @return
	 * @author Yuu
	 */
	OrderPay getBySerialNumber(long serialNumber);
}