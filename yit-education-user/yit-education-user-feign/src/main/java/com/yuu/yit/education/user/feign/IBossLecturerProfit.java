package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossLecturerProfit;

/**
 * 讲师提现日志表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossLecturerProfit extends BossLecturerProfit {

}
