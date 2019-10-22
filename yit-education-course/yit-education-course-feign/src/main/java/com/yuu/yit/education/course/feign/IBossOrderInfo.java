package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossOrderInfo;

/**
 * 订单信息表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossOrderInfo extends BossOrderInfo {

}
