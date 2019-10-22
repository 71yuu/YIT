package com.yuu.yit.education.system.common.bean.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 后台操作日志表
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class SysLogVO implements Serializable {

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
     * 操作人
     */
    private Long userNo;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 请求参数
     */
    private String content;

}
