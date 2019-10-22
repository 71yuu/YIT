package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossNavBar;

/**
 * 头部导航 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossNavBar extends BossNavBar {

}
