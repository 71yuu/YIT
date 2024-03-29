package com.yuu.yit.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.common.bean.qo.CourseChapterAuditQO;
import com.yuu.yit.education.course.common.bean.vo.CourseChapterAuditVO;
import com.yuu.yit.education.course.service.dao.CourseChapterAuditDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterAudit;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterAuditExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 章节信息-审核 
 *
 * @author Yuu
 */
@Component
public class BossCourseChapterAuditBiz {

	@Autowired
	private CourseChapterAuditDao dao;

	public Page<CourseChapterAuditVO> listForPage(CourseChapterAuditQO qo) {
	    CourseChapterAuditExample example = new CourseChapterAuditExample();
	    example.setOrderByClause(" id desc ");
        Page<CourseChapterAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterAuditVO.class);
	}

	public int save(CourseChapterAuditQO qo) {
	    CourseChapterAudit record = BeanUtil.copyProperties(qo, CourseChapterAudit.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseChapterAuditVO getById(Long id) {
	    CourseChapterAudit record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseChapterAuditVO.class);
	}

	public int updateById(CourseChapterAuditQO qo) {
	    CourseChapterAudit record = BeanUtil.copyProperties(qo, CourseChapterAudit.class);
		return dao.updateById(record);
	}
	
}
