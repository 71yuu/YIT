/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yuu
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {

	USER(1, "用户", ""), LECTURER(2, "讲师", "blue");

	private Integer code;

	private String desc;

	private String color;
}
