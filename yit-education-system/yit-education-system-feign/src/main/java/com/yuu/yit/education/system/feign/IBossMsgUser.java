package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossMsgUser;


/**
 * 站内信用户记录表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossMsgUser extends BossMsgUser {

}
