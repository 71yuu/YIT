package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossCourseCategory;

/**
 * 课程分类 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossCourseCategory extends BossCourseCategory {

}
