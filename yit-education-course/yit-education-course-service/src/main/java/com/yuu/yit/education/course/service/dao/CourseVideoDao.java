package com.yuu.yit.education.course.service.dao;

import java.util.List;

import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseVideo;
import com.yuu.yit.education.course.service.dao.impl.mapper.entity.CourseVideoExample;
import com.yuu.yit.education.util.base.Page;

public interface CourseVideoDao {
	int save(CourseVideo record);

	int deleteById(Long id);

	int updateById(CourseVideo record);

	CourseVideo getById(Long id);

	Page<CourseVideo> listForPage(int pageCurrent, int pageSize, CourseVideoExample example);

	/**
	 * 根据视频编号、课时ID查询课程视频信息
	 * 
	 * @param videoNo
	 * @param periodId
	 * @return
	 * @author Yuu
	 */
	CourseVideo getByVideoNoAndPeriodId(Long videoNo, Long periodId);

	/**
	 * 根据视频编号查询课程视频信息集合
	 * 
	 * @param videoNo
	 * @return
	 * @author Yuu
	 */
	List<CourseVideo> listByVideoNo(Long videoNo);

	/**
	 * 根据课时ID查询可用的课程视频信息集合
	 * 
	 * @param periodId
	 * @param statusId
	 * @return
	 * @author Yuu
	 */
	List<CourseVideo> listByPeriodIdAndStatusId(Long periodId, Integer statusId);

	/**
	 * 根据视频编号查询可用的课程视频信息
	 * 
	 * @param videoNo
	 * @param statusId
	 * @return
	 * @author Yuu
	 */
	CourseVideo getByVideoNoAndStatusId(Long videoNo, Integer statusId);

	/**
	 * 根据视频编号、课时ID查询可用的课程视频信息集合
	 * 
	 * @param videoNo
	 * @param periodId
	 * @param statusId
	 * @return
	 * @author Yuu
	 */
	List<CourseVideo> listByVideoNoAndNotEqualPeriodIdAndStatusId(Long videoNo, Long periodId, Integer statusId);

	/**
	 * 根据视频编号课程视频信息
	 * 
	 * @param videoNo
	 * @return
	 * @author Yuu
	 */
	CourseVideo getByVideoNo(Long videoNo);

	/**
	 * 根据章节ID、课时ID查询可用的课程视频信息集合
	 * 
	 * @param chapterId
	 * @param periodId
	 * @param statusId
	 * @return
	 * @author Yuu
	 */
	List<CourseVideo> listByChapterIdAndPeriodIdAndStatusId(Long chapterId, Long periodId, Integer statusId);
}