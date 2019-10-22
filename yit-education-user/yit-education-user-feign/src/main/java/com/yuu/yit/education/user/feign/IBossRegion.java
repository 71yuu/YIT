package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossRegion;

/**
 * 行政区域表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossRegion extends BossRegion {

}
