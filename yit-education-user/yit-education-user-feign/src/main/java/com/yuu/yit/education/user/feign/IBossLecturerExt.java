package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossLecturerExt;

/**
 * 讲师账户信息表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossLecturerExt extends BossLecturerExt {

}
