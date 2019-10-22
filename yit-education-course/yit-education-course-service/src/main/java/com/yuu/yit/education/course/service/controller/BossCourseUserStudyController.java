package com.yuu.yit.education.course.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.yuu.yit.education.course.common.bean.qo.CourseUserStudyQO;
import com.yuu.yit.education.course.common.bean.vo.CourseUserStudyVO;
import com.yuu.yit.education.course.common.interfaces.BossCourseUserStudy;
import com.yuu.yit.education.course.service.controller.biz.BossCourseUserStudyBiz;
import com.yuu.yit.education.util.base.BaseController;
import com.yuu.yit.education.util.base.Page;

/**
 * 课程用户关联表 
 *
 * @author Yuu
 */
@RestController
public class BossCourseUserStudyController extends BaseController implements BossCourseUserStudy{

	@Autowired
	private BossCourseUserStudyBiz biz;
	
	@Override
	public Page<CourseUserStudyVO> listForPage(@RequestBody CourseUserStudyQO qo){
		return biz.listForPage(qo);
	}

    @Override
	public int save(@RequestBody CourseUserStudyQO qo){
		return biz.save(qo);
	}

    @Override	
	public int deleteById(@PathVariable(value = "id") Long id){
		return biz.deleteById(id);
	}
	
    @Override	
	public int updateById(@RequestBody CourseUserStudyQO qo){
		return biz.updateById(qo);
	}
	
    @Override
	public CourseUserStudyVO getById(@PathVariable(value = "id") Long id){
		return biz.getById(id);
	}
	
}
