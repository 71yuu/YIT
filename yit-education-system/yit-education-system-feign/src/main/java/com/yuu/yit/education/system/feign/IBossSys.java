package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossSys;

/**
 * 系统配置表 
 *
 * @author YZJ
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossSys extends BossSys {

}
