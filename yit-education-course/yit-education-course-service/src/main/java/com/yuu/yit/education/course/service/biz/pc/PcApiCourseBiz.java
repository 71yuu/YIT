package com.yuu.yit.education.course.service.biz.pc;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yuu.yit.education.course.service.common.req.CourseGetREQ;
import com.yuu.yit.education.course.service.common.req.CoursePageREQ;
import com.yuu.yit.education.course.service.common.req.CourseUpdateREQ;
import com.yuu.yit.education.course.service.common.req.CourseViewREQ;
import com.yuu.yit.education.course.service.common.resq.CourseChapterPeriodViewRESQ;
import com.yuu.yit.education.course.service.common.resq.CourseChapterViewRESQ;
import com.yuu.yit.education.course.service.common.resq.CourseGetRESQ;
import com.yuu.yit.education.course.service.common.resq.CoursePageRESQ;
import com.yuu.yit.education.course.service.common.resq.CourseViewRESQ;
import com.yuu.yit.education.course.service.dao.CourseAuditDao;
import com.yuu.yit.education.course.service.dao.CourseCategoryDao;
import com.yuu.yit.education.course.service.dao.CourseChapterDao;
import com.yuu.yit.education.course.service.dao.CourseChapterPeriodDao;
import com.yuu.yit.education.course.service.dao.CourseDao;
import com.yuu.yit.education.course.service.dao.CourseIntroduceAuditDao;
import com.yuu.yit.education.course.service.dao.CourseIntroduceDao;
import com.yuu.yit.education.course.service.dao.ZoneCourseDao;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.Course;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseAudit;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseExample;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseExample.Criteria;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.ZoneCourse;
import com.yuu.yit.education.user.common.bean.vo.LecturerVO;
import com.yuu.yit.education.user.feign.IBossLecturer;
import com.yuu.yit.education.util.base.Page;
import com.yuu.yit.education.util.base.PageUtil;
import com.yuu.yit.education.util.base.Result;
import com.yuu.yit.education.util.enums.IsFreeEnum;
import com.yuu.yit.education.util.enums.ResultEnum;
import com.yuu.yit.education.util.enums.StatusIdEnum;
import com.yuu.yit.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息
 */
@Component
public class PcApiCourseBiz {

	@Autowired
	private IBossLecturer bossLecturer;

	@Autowired
	private ZoneCourseDao zoneCourseDao;
	@Autowired
	private CourseDao dao;
	@Autowired
	private CourseAuditDao courseAuditDao;
	@Autowired
	private CourseChapterDao courseChapterDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseIntroduceAuditDao courseIntroduceAuditDao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;

	/**
	 * 分页列出
	 * 
	 * @param req
	 * @return
	 */
	public Result<Page<CoursePageRESQ>> list(CoursePageREQ req) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		if (req.getCategoryId1() != null) {
			c.andCategoryId1EqualTo(req.getCategoryId1());
		}
		if (!StringUtils.isEmpty(req.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(req.getCourseName()));
		}
		if (req.getStatusId() != null) {
			c.andStatusIdEqualTo(req.getStatusId());
		}
		if (req.getIsFree() != null) {
			c.andIsFreeEqualTo(req.getIsFree());
		}
		if (req.getIsPutaway() != null) {
			c.andIsPutawayEqualTo(req.getIsPutaway());
		}
		example.setOrderByClause(" status_id desc, is_putaway desc, course_sort desc, id desc ");
		Page<Course> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CoursePageRESQ> listForPage = PageUtil.transform(page, CoursePageRESQ.class);
		// 获取分类名称
		for (CoursePageRESQ resq : listForPage.getList()) {
			if (req.getZoneId() != null) {
				// 校验专区是否存在课程
				ZoneCourse zoneCourse = zoneCourseDao.getZoneIdAndCourseId(resq.getId(), req.getZoneId());
				if (ObjectUtil.isNull(zoneCourse)) {
					// 不存在
					resq.setIsAddZoneCourse(0);
				} else {
					// 存在
					resq.setIsAddZoneCourse(1);
				}
			}
			LecturerVO lecturer = bossLecturer.getByLecturerUserNo(resq.getLecturerUserNo());
			if (ObjectUtil.isNotNull(lecturer)) {
				resq.setLecturerName(lecturer.getLecturerName());
			}
			if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName1(courseCategory.getCategoryName());
				}
			}
			if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName2(courseCategory.getCategoryName());
				}
			}
			if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
				if (!StringUtils.isEmpty(courseCategory)) {
					resq.setCategoryName3(courseCategory.getCategoryName());
				}
			}
		}
		return Result.success(listForPage);
	}

	@Transactional
	public Result<Integer> update(CourseUpdateREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Course course = dao.getById(req.getId());
		if (ObjectUtil.isNull(course)) {
			return Result.error("找不到课程信息");
		}
		if (IsFreeEnum.FREE.getCode().equals(req.getIsFree())) {
			req.setCourseOriginal(BigDecimal.ZERO);
			req.setCourseDiscount(BigDecimal.ZERO);
		}
		Course record = BeanUtil.copyProperties(req, Course.class);
		int result = dao.updateById(record);
		if (result > 0) {
			// 同步更新审核表
			CourseAudit courseAudit = BeanUtil.copyProperties(req, CourseAudit.class);
			courseAudit.setGmtCreate(null);
			courseAudit.setGmtModified(null);
			int recordAudit = courseAuditDao.updateById(courseAudit);
			if (recordAudit < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
		}

		if (StringUtils.hasText(req.getIntroduce())) {
			// 更新课程介绍
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(course.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduce)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduce.setId(course.getIntroduceId());
			courseIntroduce.setIntroduce(req.getIntroduce());
			int results = courseIntroduceDao.updateById(courseIntroduce);
			if (results < 0) {
				return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
			}
			// 同步更新审核表
			CourseIntroduceAudit courseIntroduceAudit = courseIntroduceAuditDao.getById(course.getIntroduceId());
			if (ObjectUtil.isNull(courseIntroduceAudit)) {
				return Result.error("找不到课程简介信息");
			}
			courseIntroduceAudit.setGmtCreate(null);
			courseIntroduceAudit.setGmtModified(null);
			courseIntroduceAudit.setId(course.getIntroduceId());
			courseIntroduceAudit.setIntroduce(req.getIntroduce());
			courseIntroduceAuditDao.updateById(courseIntroduceAudit);
		}
		return Result.success(result);
	}

	/**
	 * 查看(课程修改使用)
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseGetRESQ> get(CourseGetREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		// 根据id查找课程信息
		Course record = dao.getById(req.getId());
		CourseGetRESQ resq = BeanUtil.copyProperties(record, CourseGetRESQ.class);
		if (ObjectUtil.isNotNull(resq)) {
			// 获取分类名称
			if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
				resq.setCategoryName1(courseCategory.getCategoryName());
			}
			if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
				resq.setCategoryName2(courseCategory.getCategoryName());
			}
			if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
				resq.setCategoryName3(courseCategory.getCategoryName());
			}
			// 根据id查找课程简介信息
			CourseIntroduce courseIntroduce = courseIntroduceDao.getById(record.getIntroduceId());
			// 把课程简介带回课程信息
			if (ObjectUtil.isNotNull(courseIntroduce)) {
				resq.setIntroduce(courseIntroduce.getIntroduce());
			}
		}
		return Result.success(resq);
	}

	/**
	 * 查看详情
	 * 
	 * @param req
	 * @return
	 */
	public Result<CourseViewRESQ> view(CourseViewREQ req) {
		if (req.getId() == null) {
			return Result.error("ID不能为空");
		}
		Course record = dao.getById(req.getId());
		CourseViewRESQ resq = BeanUtil.copyProperties(record, CourseViewRESQ.class);
		// 获取分类名称
		if (resq.getCategoryId1() != null && resq.getCategoryId1() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId1());
			resq.setCategoryName1(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId2() != null && resq.getCategoryId2() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId2());
			resq.setCategoryName2(courseCategory.getCategoryName());
		}
		if (resq.getCategoryId3() != null && resq.getCategoryId3() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(resq.getCategoryId3());
			resq.setCategoryName3(courseCategory.getCategoryName());
		}
		// 章节
		List<CourseChapter> ChapterList = courseChapterDao.listByCourseIdAndStatusId(resq.getId(),
				StatusIdEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<CourseChapterViewRESQ> courseChapterVOList = PageUtil.copyList(ChapterList,
					CourseChapterViewRESQ.class);
			for (CourseChapterViewRESQ courseChapter : courseChapterVOList) {
				// 课时
				List<CourseChapterPeriod> periodList = courseChapterPeriodDao
						.listByChapterIdAndStatusId(courseChapter.getId(), StatusIdEnum.YES.getCode());
				courseChapter
						.setCourseChapterPeriodList(PageUtil.copyList(periodList, CourseChapterPeriodViewRESQ.class));
			}
			resq.setCourseChapterList(courseChapterVOList);
		}
		return Result.success(resq);
	}

}
