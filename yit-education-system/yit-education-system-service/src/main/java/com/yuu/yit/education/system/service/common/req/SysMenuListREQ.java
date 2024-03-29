package com.yuu.yit.education.system.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单信息-列出
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class SysMenuListREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称", required = false)
	private String menuName;

}
