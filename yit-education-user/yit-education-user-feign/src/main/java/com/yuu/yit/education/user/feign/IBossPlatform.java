package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossPlatform;

/**
 * 平台信息
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossPlatform extends BossPlatform {

}
