package com.yuu.yit.education.course.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程用户关联表
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class CourseUserStudyVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	private Integer statusId;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 课程ID
	 */
	private Long courseId;
	/**
	 * 用户编号
	 */
	private Long userNo;
	/**
	 * 总课时数
	 */
	private Integer periodTotal;
	/**
	 * 已学习课时数
	 */
	private Integer periodStudy;

}
