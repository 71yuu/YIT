package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossCourseUserStudy;

/**
 * 课程用户关联表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossCourseUserStudy extends BossCourseUserStudy {

}
