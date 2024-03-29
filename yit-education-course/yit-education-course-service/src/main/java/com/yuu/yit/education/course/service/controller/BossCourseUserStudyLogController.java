package com.yuu.yit.education.course.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.course.common.bean.qo.CourseUserStudyLogQO;
import com.yuu.yit.education.course.common.bean.vo.CourseUserStudyLogVO;
import com.yuu.yit.education.course.common.interfaces.BossCourseUserStudyLog;
import com.yuu.yit.education.course.service.controller.biz.BossCourseUserStudyLogBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 课程用户学习日志
 *
 * @author Yuu
 */
@RestController
public class BossCourseUserStudyLogController extends BaseController implements BossCourseUserStudyLog {

	@Autowired
	private BossCourseUserStudyLogBiz biz;

	@Override
	public Page<CourseUserStudyLogVO> listForPage(@RequestBody CourseUserStudyLogQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody CourseUserStudyLogQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody CourseUserStudyLogQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public CourseUserStudyLogVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public Page<CourseUserStudyLogVO> courseList(@RequestBody CourseUserStudyLogQO qo) {
		return biz.courseList(qo);
	}

	@Override
	public List<CourseUserStudyLogVO> countCourseIdByGmtCreate(@RequestBody CourseUserStudyLogQO qo) {
		return biz.countCourseIdByGmtCreate(qo);
	}

	@Override
	public Page<CourseUserStudyLogVO> periodList(@RequestBody CourseUserStudyLogQO qo) {
		return biz.periodList(qo);
	}

	@Override
	public List<CourseUserStudyLogVO> countPeriodNoByCourseIdAndGmtCreate(@RequestBody CourseUserStudyLogQO qo) {
		return biz.countPeriodNoByCourseIdAndGmtCreate(qo);
	}

}
