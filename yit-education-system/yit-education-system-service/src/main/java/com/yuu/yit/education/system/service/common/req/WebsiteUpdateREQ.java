package com.yuu.yit.education.system.service.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点信息-更新
 *
 */
@Data
@Accessors(chain = true)
public class WebsiteUpdateREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id", required = true)
	private Long id;
	/**
	 * 状态(1有效, 0无效)
	 */
	@ApiModelProperty(value = "状态(1有效, 0无效)")
	private Integer statusId;
	/**
	 * 站点标题
	 */
	@ApiModelProperty(value = "站点标题")
	private String websiteTitle;
	/**
	 * 站点关键词
	 */
	@ApiModelProperty(value = "站点关键词")
	private String websiteKeyword;
	/**
	 * 站点描述
	 */
	@ApiModelProperty(value = "站点描述")
	private String websiteDesc;
	/**
	 * 站点版权
	 */
	@ApiModelProperty(value = "站点版权")
	private String copyright;
	/**
	 * 站点微信
	 */
	@ApiModelProperty(value = "站点微信")
	private String weixin;
	/**
	 * logoImg
	 */
	@ApiModelProperty(value = "logoImg")
	private String logoImg;
	/**
	 * logoIco
	 */
	@ApiModelProperty(value = "logoIco")
	private String logoIco;
	/**
	 * 用户协议
	 */
	@ApiModelProperty(value = "用户协议")
	private String userAgreement;
	/**
	 * 招募标题
	 */
	@ApiModelProperty(value = "招募标题")
	private String recruitTitle;
	/**
	 * 招募信息
	 */
	@ApiModelProperty(value = "招募信息")
	private String recruitInfo;
	/**
	 * 入驻协议
	 */
	@ApiModelProperty(value = "入驻协议")
	private String entryAgreement;

	/**
	 * 个人博客
	 */
	@ApiModelProperty(value = "个人博客")
	private String blog;

	/**
	 * Github 地址
	 */
	private String github;
}
