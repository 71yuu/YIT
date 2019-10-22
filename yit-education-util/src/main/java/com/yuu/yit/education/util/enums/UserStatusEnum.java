package com.yuu.yit.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态
 *
 * @author by Yuu
 * @Classname UserStatusEnum
 * @Date 2019/10/21 1:25
 * @see com.yuu.yit.education.util.enums
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    NORMAL(1, "正常"), BAN(0, "已被封禁");

    private Integer code;

    private String desc;

}
