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
public enum HasNoticeEnum {

	YES(1, "已提醒"), NO(0, "未提醒");

	private Integer code;

	private String desc;

}
