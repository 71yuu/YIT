/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.user.service.common.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 讲师信息
 * </p>
 *
 * @author Yuu123
 */
@Data
@Accessors(chain = true)
public class LecturerViewBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "讲师主键", required = true)
	private Long id;

	/**
	 * 讲师用户编号
	 */
	@ApiModelProperty(value = "讲师用户编号", required = true)
	private Long lecturerUserNo;
	/**
	 * 讲师名称
	 */
	@ApiModelProperty(value = "讲师名称", required = false)
	private String lecturerName;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像", required = false)
	private String headImgUrl;

	/**
	 * 简介
	 */
	@ApiModelProperty(value = "简介", required = false)
	private String introduce;

}
