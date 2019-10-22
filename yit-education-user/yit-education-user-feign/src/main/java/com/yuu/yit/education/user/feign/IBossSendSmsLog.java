package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossSendSmsLog;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossSendSmsLog extends BossSendSmsLog {

}
