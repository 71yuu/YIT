package com.yuu.yit.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuu.yit.education.course.common.bean.qo.CourseQO;
import com.yuu.yit.education.course.common.bean.vo.CourseVO;
import com.yuu.yit.education.course.common.interfaces.BossCourse;
import com.yuu.yit.education.course.service.controller.biz.BossCourseBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 课程信息
 *
 * @author Yuu
 */
@RestController
public class BossCourseController extends BaseController implements BossCourse {

	@Autowired
	private BossCourseBiz biz;

	@Override
	public Page<CourseVO> listForPage(@RequestBody CourseQO qo) {
		return biz.listForPage(qo);
	}

	@Override
	public int save(@RequestBody CourseQO qo) {
		return biz.save(qo);
	}

	@Override
	public int deleteById(@PathVariable(value = "id") Long id) {
		return biz.deleteById(id);
	}

	@Override
	public int updateById(@RequestBody CourseQO qo) {
		return biz.updateById(qo);
	}

	@Override
	public CourseVO getById(@PathVariable(value = "id") Long id) {
		return biz.getById(id);
	}

	@Override
	public CourseVO getByCourseId(@PathVariable(value = "id") Long id) {
		return biz.getByCourseId(id);
	}

}
