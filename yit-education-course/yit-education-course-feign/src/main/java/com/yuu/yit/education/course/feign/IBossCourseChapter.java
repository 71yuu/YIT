package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossCourseChapter;

/**
 * 章节信息 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossCourseChapter extends BossCourseChapter {

}
