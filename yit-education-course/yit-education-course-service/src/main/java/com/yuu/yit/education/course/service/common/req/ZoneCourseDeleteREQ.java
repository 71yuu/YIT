package com.yuu.yit.education.course.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区课程-删除
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class ZoneCourseDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;

}
