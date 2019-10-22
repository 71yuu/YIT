package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossCourseChapterPeriodAudit;

/**
 * 课时信息-审核 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossCourseChapterPeriodAudit extends BossCourseChapterPeriodAudit {

}
