package com.yuu.yit.education.system.service.common.bo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单角色关联表
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class SysEnumBO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 枚举名称
     */
    private String enumName;
}
