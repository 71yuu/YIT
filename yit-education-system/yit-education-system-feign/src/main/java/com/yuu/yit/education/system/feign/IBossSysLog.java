package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossSysLog;

/**
 * 后台操作日志表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossSysLog extends BossSysLog {

}
