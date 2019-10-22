package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossCourseChapterAudit;

/**
 * 章节信息-审核 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossCourseChapterAudit extends BossCourseChapterAudit {

}
