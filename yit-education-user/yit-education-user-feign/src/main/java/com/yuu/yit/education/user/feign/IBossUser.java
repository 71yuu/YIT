package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossUser;

/**
 * 用户基本信息
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossUser extends BossUser {

}
