package com.yuu.yit.education.course.service.dao;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseIntroduceExample;
import com.yuu.yit.education.util.base.Page;

public interface CourseIntroduceDao {
    int save(CourseIntroduce record);

    int deleteById(Long id);

    int updateById(CourseIntroduce record);

    CourseIntroduce getById(Long id);

    Page<CourseIntroduce> listForPage(int pageCurrent, int pageSize, CourseIntroduceExample example);
}