package com.yuu.yit.education.user.common.bean.qo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class UserExtQO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 当前页
     */
    private int pageCurrent;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 用户编号
     */
    private Long userNo;
    /**
     * 用户类型(1用户，2讲师)
     */
    private Integer userType;
    /**
     * 用户手机
     */
    private String mobile;
    /**
     * 性别(1男，2女，3保密)
     */
    private Integer sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像地址
     */
    private String headImgUrl;
    /**
     * 备注
     */
    private String remark;
    
	private String beginGmtCreate;
	private String endGmtCreate;
}
