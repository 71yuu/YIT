package com.yuu.yit.education.system.common.bean.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 设置信息集合，有站点信息，讲师招募信息，代理招募信息
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class WebsiteQO implements Serializable {

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
