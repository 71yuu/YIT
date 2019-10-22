package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossOrderPay;

/**
 * 订单支付信息表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossOrderPay extends BossOrderPay {

}
