package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossDicList;

/**
 * 数据字典明细表 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossDicList extends BossDicList {

}
