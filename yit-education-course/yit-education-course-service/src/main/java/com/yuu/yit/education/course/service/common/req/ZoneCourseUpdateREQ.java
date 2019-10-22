package com.yuu.yit.education.course.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程-更新
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class ZoneCourseUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	/**
	 * 状态(1:正常;0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常;0:禁用)", required = false)
	private Integer statusId;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序", required = false)
	private Integer sort;
}
