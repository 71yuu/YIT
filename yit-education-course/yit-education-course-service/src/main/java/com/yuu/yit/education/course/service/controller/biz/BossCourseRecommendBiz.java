package com.yuu.yit.education.course.service.controller.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuu.yit.education.course.common.bean.qo.CourseRecommendQO;
import com.yuu.yit.education.course.common.bean.vo.CourseRecommendVO;
import com.yuu.yit.education.course.service.dao.CourseDao;
import com.yuu.yit.education.course.service.dao.CourseRecommendDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Course;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseRecommend;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseRecommendExample;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseRecommendExample.Criteria;
import com.yuu.yit.education.util.base.BaseException;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.enums.StatusIdEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程推荐
 *
 * @author Yuu
 */
@Component
public class BossCourseRecommendBiz {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseRecommendDao dao;

	public Page<CourseRecommendVO> listForPage(CourseRecommendQO qo) {
		CourseRecommendExample example = new CourseRecommendExample();
		Criteria c = example.createCriteria();
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		}
		c.andCategoryIdEqualTo(qo.getCategoryId());
		example.setOrderByClause("status_id desc, sort desc, id desc");
		Page<CourseRecommend> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		Page<CourseRecommendVO> listForPage = PageUtil.transform(page, CourseRecommendVO.class);
		for (CourseRecommendVO vo : listForPage.getList()) {
			Course course = courseDao.getById(vo.getCourseId());
			if (ObjectUtil.isNotNull(course)) {
				vo.setCourseName(course.getCourseName());
			}
		}
		return listForPage;
	}

	public int save(CourseRecommendQO qo) {
		List<CourseRecommend> courseRecommend = dao.listByCategoryIdAndStatusId(qo.getCategoryId(), StatusIdEnum.YES.getCode());
		if (courseRecommend.size() >= 5) {
			throw new BaseException("课程只展示5个");
		}
		CourseRecommend record = BeanUtil.copyProperties(qo, CourseRecommend.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public int updateById(CourseRecommendQO qo) {
		CourseRecommend record = BeanUtil.copyProperties(qo, CourseRecommend.class);
		return dao.updateById(record);
	}

	public CourseRecommendVO getById(Long id) {
		CourseRecommend record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseRecommendVO.class);
	}

}
