package com.yuu.yit.education.course.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专区-删除
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class ZoneDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;

}
