package com.yuu.yit.education.course.service.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditListBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 审核状态(0:待审核,1:审核通过,2:审核不通过)
	 */
	@ApiModelProperty(value = "审核状态(0:待审核,1:审核通过,2:审核不通过)")
	private Integer auditStatus;
	/**
	 * 讲师用户编号
	 */
	@ApiModelProperty(value = "讲师用户编号", required = true)
	private Long lecturerUserNo;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private Integer pageCurrent = 1;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(value = "每页记录数", required = true)
	private Integer pageSize = 20;
}
