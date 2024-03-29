package com.yuu.yit.education.course.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class PeriodUploadDocBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课时id
	 */
	@ApiModelProperty(value = "课时id")
	private Long periodId;

}
