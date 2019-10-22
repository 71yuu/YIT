package com.yuu.yit.education.user.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师信息-审核查看
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class LecturerAuditViewREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private Long id;

}
