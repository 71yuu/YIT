package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossSysRole;

/**
 * 角色信息 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossSysRole extends BossSysRole {

}
