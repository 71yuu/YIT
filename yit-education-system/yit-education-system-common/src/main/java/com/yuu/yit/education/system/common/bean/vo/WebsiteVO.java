package com.yuu.yit.education.system.common.bean.vo;

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
public class WebsiteVO implements Serializable {

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
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(1有效, 0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * logoIco
     */
    private String logoIco;
    /**
     * logoImg
     */
    private String logoImg;
    /**
     * 站点标题
     */
    private String websiteTitle;
    /**
     * 站点关键词
     */
    private String websiteKeyword;
    /**
     * 站点描述
     */
    private String websiteDesc;
    /**
     * 站点版权
     */
    private String copyright;
    /**
     * 站点微信
     */
    private String weixin;
    /**
	 * 用户协议
	 */
	private String userAgreement;
	/**
	 * 招募标题
	 */
	private String recruitTitle;
	/**
	 * 招募信息
	 */
	private String recruitInfo;
	/**
	 * 入驻协议
	 */
	private String entryAgreement;

    /**
     * 个人博客
     */
	private String blog;

    /**
     * Github 地址
     */
	private String github;
    
}
