package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossSysRoleUser;

/**
 * 角色用户关联表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossSysRoleUser extends BossSysRoleUser {

}
