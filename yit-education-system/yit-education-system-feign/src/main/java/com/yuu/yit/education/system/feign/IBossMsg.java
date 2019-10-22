package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossMsg;


/**
 * 站内信息表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossMsg extends BossMsg {

}
