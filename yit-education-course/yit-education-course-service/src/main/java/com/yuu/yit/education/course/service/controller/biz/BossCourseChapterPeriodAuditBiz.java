package com.yuu.yit.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.common.bean.qo.CourseChapterPeriodAuditQO;
import com.yuu.yit.education.course.common.bean.vo.CourseChapterPeriodAuditVO;
import com.yuu.yit.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAuditExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 课时信息-审核
 *
 * @author Yuu
 */
@Component
public class BossCourseChapterPeriodAuditBiz {

	@Autowired
	private CourseChapterPeriodAuditDao dao;

	public Page<CourseChapterPeriodAuditVO> listForPage(CourseChapterPeriodAuditQO qo) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		example.setOrderByClause(" id desc ");
		Page<CourseChapterPeriodAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, CourseChapterPeriodAuditVO.class);
	}

	public int save(CourseChapterPeriodAuditQO qo) {
		CourseChapterPeriodAudit record = BeanUtil.copyProperties(qo, CourseChapterPeriodAudit.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseChapterPeriodAuditVO getById(Long id) {
		CourseChapterPeriodAudit record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseChapterPeriodAuditVO.class);
	}

	public int updateById(CourseChapterPeriodAuditQO qo) {
		CourseChapterPeriodAudit record = BeanUtil.copyProperties(qo, CourseChapterPeriodAudit.class);
		return dao.updateById(record);
	}

	public CourseChapterPeriodAuditVO getByVideoNo(Long videoNo) {
		CourseChapterPeriodAudit record = dao.getByVideoNo(videoNo);
		return BeanUtil.copyProperties(record, CourseChapterPeriodAuditVO.class);
	}
}
