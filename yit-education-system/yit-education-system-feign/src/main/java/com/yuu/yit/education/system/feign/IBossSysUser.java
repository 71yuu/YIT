package com.yuu.yit.education.system.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.system.common.interfaces.BossSysUser;

/**
 * 后台用户信息 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-system-service")
public interface IBossSysUser extends BossSysUser {

}
