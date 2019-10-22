package com.yuu.yit.education.user.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.user.common.interfaces.BossLecturerAudit;

/**
 * 讲师信息-审核 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-user-service")
public interface IBossLecturerAudit extends BossLecturerAudit {

}
