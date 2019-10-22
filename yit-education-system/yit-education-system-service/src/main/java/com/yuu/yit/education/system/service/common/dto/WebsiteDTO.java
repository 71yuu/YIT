package com.yuu.yit.education.system.service.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 站点信息
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class WebsiteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;
    /**
     * 状态(1有效, 0无效)
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
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
     * 个人博客地址
     */
    @ApiModelProperty(value = "个人博客地址")
    private String blog;

    /**
     * GitHub 地址
     */
    @ApiModelProperty(value = "GitHub 地址")
    private String github;
}
