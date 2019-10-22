package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossZoneCourse;

/**
 * 专区课程关联表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossZoneCourse extends BossZoneCourse {

}
