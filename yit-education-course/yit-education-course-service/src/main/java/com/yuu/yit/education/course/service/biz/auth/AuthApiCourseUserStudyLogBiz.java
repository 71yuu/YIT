package com.yuu.yit.education.course.service.biz.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.service.common.bo.auth.AuthCourseUserStudyLogPageBO;
import com.yuu.yit.education.course.service.common.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.yuu.yit.education.course.service.dao.CourseChapterPeriodDao;
import com.yuu.yit.education.course.service.dao.CourseDao;
import com.yuu.yit.education.course.service.dao.CourseUserStudyLogDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Course;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseUserStudyLog;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample.Criteria;
import com.yuu.yit.education.util.base.BaseBiz;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;

/**
 * 课程信息
 *
 * @author Yuu
 */
@Component
public class AuthApiCourseUserStudyLogBiz extends BaseBiz {

	@Autowired
	private CourseUserStudyLogDao courseUserStudyLogDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseChapterPeriodDao periodDao;

	/**
	 * 最近学习日志分页列出接口
	 * 
	 * @param bo
	 * @return
	 * @author Yuu
	 */
	public Result<Page<AuthCourseUserStudyLogPageDTO>> list(AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
		CourseUserStudyLogExample example = new CourseUserStudyLogExample();
		Criteria c = example.createCriteria();
		if (authCourseUserStudyLogPageBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}
		c.andUserNoEqualTo(authCourseUserStudyLogPageBO.getUserNo());
		Page<CourseUserStudyLog> page = courseUserStudyLogDao.listForPage(authCourseUserStudyLogPageBO.getPageCurrent(), authCourseUserStudyLogPageBO.getPageSize(), example);
		Page<AuthCourseUserStudyLogPageDTO> dtoList = PageUtil.transform(page, AuthCourseUserStudyLogPageDTO.class);
		for (AuthCourseUserStudyLogPageDTO dto : dtoList.getList()) {
			// 查看课程信息
			Course course = courseDao.getById(dto.getCourseId());
			// 查看课时信息
			CourseChapterPeriod period = periodDao.getById(dto.getPeriodId());

			dto.setCourseName(course.getCourseName());
			dto.setPeriodName(period.getPeriodName());
		}
		return Result.success(dtoList);
	}

}
