package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossUserLogModified;

/**
 * 用户修改日志 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossUserLogModified extends BossUserLogModified {

}
