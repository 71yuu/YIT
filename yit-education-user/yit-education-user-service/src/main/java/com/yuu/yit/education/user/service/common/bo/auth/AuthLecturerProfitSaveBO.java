package com.yuu.yit.education.user.service.common.bo.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师提现日志表
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class AuthLecturerProfitSaveBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 讲师用户编号
	 */
	@ApiModelProperty(value = "讲师用户编号", required = true)
	private Long lecturerUserNo;
	/**
	 * 提现金额
	 */
	@ApiModelProperty(value = "提现金额", required = true)
	private BigDecimal extractMoney;
	/**
	 * 银行卡手机号
	 */
	@ApiModelProperty(value = "银行卡手机号", required = true)
	private String bankCardNo;
	/**
	 * 验证码
	 */
	@ApiModelProperty(value = "验证码", required = true)
	private String smsCode;
	/**
	 * clientId
	 */
	@ApiModelProperty(value = "clientId", required = true)
	private String clientId;
}
