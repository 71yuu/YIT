package com.yuu.yit.education.course.service.dao;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseIntroduceAuditExample;
import com.yuu.yit.education.util.base.Page;

public interface CourseIntroduceAuditDao {
    int save(CourseIntroduceAudit record);

    int deleteById(Long id);

    int updateById(CourseIntroduceAudit record);

    CourseIntroduceAudit getById(Long id);

    Page<CourseIntroduceAudit> listForPage(int pageCurrent, int pageSize, CourseIntroduceAuditExample example);
}