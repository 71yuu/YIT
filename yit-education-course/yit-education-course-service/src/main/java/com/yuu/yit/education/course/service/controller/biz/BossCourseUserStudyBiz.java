package com.yuu.yit.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.common.bean.qo.CourseUserStudyQO;
import com.yuu.yit.education.course.common.bean.vo.CourseUserStudyVO;
import com.yuu.yit.education.course.service.dao.CourseUserStudyDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseUserStudy;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseUserStudyExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 课程用户关联表 
 *
 * @author Yuu
 */
@Component
public class BossCourseUserStudyBiz {

	@Autowired
	private CourseUserStudyDao dao;

	public Page<CourseUserStudyVO> listForPage(CourseUserStudyQO qo) {
	    CourseUserStudyExample example = new CourseUserStudyExample();
	    example.setOrderByClause(" id desc ");
        Page<CourseUserStudy> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseUserStudyVO.class);
	}

	public int save(CourseUserStudyQO qo) {
	    CourseUserStudy record = BeanUtil.copyProperties(qo, CourseUserStudy.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseUserStudyVO getById(Long id) {
	    CourseUserStudy record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseUserStudyVO.class);
	}

	public int updateById(CourseUserStudyQO qo) {
	    CourseUserStudy record = BeanUtil.copyProperties(qo, CourseUserStudy.class);
		return dao.updateById(record);
	}
	
}
