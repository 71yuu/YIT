package com.yuu.yit.education.user.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 平台信息-查看
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class PlatformDeleteREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
}
