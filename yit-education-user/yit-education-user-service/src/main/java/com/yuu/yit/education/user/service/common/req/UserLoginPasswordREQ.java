/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.user.service.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author Yuu123
 */
@Data
public class UserLoginPasswordREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号", required = true)
	private String mobile;
	/**
	 * 登录密码
	 */
	@ApiModelProperty(value = "密码", required = true)
	private String password;

	/**
	 * 登录 IP 地址
	 */
	private String ip;

}
