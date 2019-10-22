package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossZone;

/**
 * 专区 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossZone extends BossZone {

}
