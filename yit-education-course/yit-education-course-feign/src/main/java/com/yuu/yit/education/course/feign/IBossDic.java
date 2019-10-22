package com.yuu.yit.education.course.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.yuu.yit.education.course.common.interfaces.BossDic;

/**
 * 数据字典 
 *
 * @author Yuu
 */
@FeignClient(value = "yit-education-course-service")
public interface IBossDic extends BossDic {

}
