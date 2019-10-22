/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.yuu.yit.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 缓存前缀
 * 
 * @author Yuu
 */
@Getter
@AllArgsConstructor
public enum RedisPreEnum {

	SYS_MSG_SEND("sys_msg_send_", "发送站内信-sys_msg_send_"), //

	SYS_MSG_SEND_NUM("sys_msg_send_num_", "发送站内信key数量-sys_msg_send_num_"), //

	SYS_MSG("sys_msg_", "站内信-sys_msg"), //

	ADMINI_MENU("admini_menu_", "管理员菜单-admini_menu_"), //

	LOGIN_PASS_ERROR("pass_error_", "用户登录密码错误次数-pass_error_"),

	BAN_LOGIN("ban_login_", "用户被禁止登录-ban_login_"),

	AUTO_LOGIN("auto_login_", "用户自动登录_auto_login_");

	private String code;

	private String desc;

}
