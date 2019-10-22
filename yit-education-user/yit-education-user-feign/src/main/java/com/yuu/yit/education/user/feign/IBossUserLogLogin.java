package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossUserLogLogin;

/**
 * 用户错误登录日志 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossUserLogLogin extends BossUserLogLogin {

}
