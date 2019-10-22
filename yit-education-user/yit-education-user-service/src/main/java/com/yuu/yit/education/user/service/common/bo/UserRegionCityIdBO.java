/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.user.service.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author Yuu123
 */
@Data
@Accessors(chain = true)
public class UserRegionCityIdBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * cityId
	 */
	@ApiModelProperty(value = "cityId", required = true)
	private Integer cityId;

}
