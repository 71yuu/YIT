package com.yuu.yit.education.course.common.bean.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class CourseVO implements Serializable {

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
	 * 讲师用户编码
	 */
	private Long lecturerUserNo;
	/**
	 * 一级分类名
	 */
	private String categoryName1;
	/**
	 * 二级分类名
	 */
	private String categoryName2;
	/**
	 * 三级分类名
	 */
	private String categoryName3;
	/**
	 * 一级分类ID
	 */
	private Long categoryId1;
	/**
	 * 二级分类ID
	 */
	private Long categoryId2;
	/**
	 * 三级分类ID
	 */
	private Long categoryId3;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程封面
	 */
	private String courseLogo;
	/**
	 * 课程介绍ID
	 */
	private Long introduceId;
	/**
	 * 是否免费：1免费，0收费
	 */
	private Integer isFree;
	/**
	 * 原价
	 */
	private BigDecimal courseOriginal;
	/**
	 * 优惠价
	 */
	private BigDecimal courseDiscount;
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	private Integer isPutaway;
	/**
	 * 课程排序
	 */
	private Integer courseSort;
	/**
	 * 购买人数
	 */
	private Integer countBuy;
	/**
	 * 学习人数
	 */
	private Integer countStudy;
	/**
	 * 总课时数
	 */
	private Integer periodTotal;
	/**
	 * 课程标签名称
	 */
	private String labelName;
	/**
	 * 章节集合
	 */
	private List<CourseChapterVO> courseChapterVOList;
	/**
	 * 课程简介
	 */
	private String introduce;

}
