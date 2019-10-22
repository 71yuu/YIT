package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossMsgTemplate;


/**
 * 消息模板 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossMsgTemplate extends BossMsgTemplate {

}
