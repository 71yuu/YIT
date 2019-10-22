package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossUserExt;

/**
 * 用户教育信息
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossUserExt extends BossUserExt {

}
