package com.yuu.yit.education.user.service.common.bo.auth;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户错误登录日志
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class AuthUserLogLoginBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 用户编号
     */
    private Long userNo;
    /**
     * 客户端ID
     */
    private String platformId;
    /**
     * 登录状态(1成功，0失败)
     */
    private Integer loginStatus;
    /**
     * 登录IP
     */
    private String loginIp;
}
