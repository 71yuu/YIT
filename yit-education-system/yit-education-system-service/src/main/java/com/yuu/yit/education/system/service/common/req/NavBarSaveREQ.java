package com.yuu.yit.education.system.service.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 头部导航-保存
 *
 */
@Data
public class NavBarSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 导航标题
	 */
	@ApiModelProperty(value = "导航标题", required = true)
	private String navTitle;

	/**
	 * 跳转方式
	 */
	@ApiModelProperty(value = "跳转方式", required = true)
	private String target;

	/**
	 * 导航url
	 */
	@ApiModelProperty(value = "导航url", required = true)
	private String navUrl;

	/**
	 * 导航排序值
	 */
	@ApiModelProperty(value = "排序值", required = true)
	private Integer sort;

}
