package com.yuu.yit.education.system.service.common.resq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单角色关联表-列出
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class SysMenuRoleListRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单角色关联表集合
	 */
	@ApiModelProperty(value = "菜单角色关联菜单ID集合")
	private List<String> list = new ArrayList<>();
}
