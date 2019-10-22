package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossCourseIntroduceAudit;

/**
 * 课程介绍信息 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossCourseIntroduceAudit extends BossCourseIntroduceAudit {

}
