package com.yuu.yit.education.course.service.controller.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.common.bean.qo.CourseChapterPeriodQO;
import com.yuu.yit.education.course.common.bean.vo.CourseChapterPeriodVO;
import com.yuu.yit.education.course.service.dao.CourseChapterPeriodDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.tools.BeanUtil;

/**
 * 课时信息 
 *
 * @author Yuu
 */
@Component
public class BossCourseChapterPeriodBiz {

	@Autowired
	private CourseChapterPeriodDao dao;

	public Page<CourseChapterPeriodVO> listForPage(CourseChapterPeriodQO qo) {
	    CourseChapterPeriodExample example = new CourseChapterPeriodExample();
	    example.setOrderByClause(" id desc ");
        Page<CourseChapterPeriod> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterPeriodVO.class);
	}

	public int save(CourseChapterPeriodQO qo) {
	    CourseChapterPeriod record = BeanUtil.copyProperties(qo, CourseChapterPeriod.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseChapterPeriodVO getById(Long id) {
	    CourseChapterPeriod record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseChapterPeriodVO.class);
	}

	public int updateById(CourseChapterPeriodQO qo) {
	    CourseChapterPeriod record = BeanUtil.copyProperties(qo, CourseChapterPeriod.class);
		return dao.updateById(record);
	}

	/**
	 * 根据视频编号查询课时正式表信息
	 * 
	 * @param videoNo
	 * @return
	 * @author Yuu
	 */
	public CourseChapterPeriodVO getByVideoNo(Long videoNo) {
		CourseChapterPeriod record = dao.getByVideoNo(videoNo);
		return BeanUtil.copyProperties(record, CourseChapterPeriodVO.class);
	}
	
}
