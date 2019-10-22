package com.yuu.yit.education.system.service.dao.impl.mapper.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 网站信息实体类
 */
@Data
public class Website implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer statusId;

    private Integer sort;

    private String logoIco;

    private String logoImg;

    private String websiteTitle;

    private String websiteKeyword;

    private String websiteDesc;

    private String copyright;

    private String weixin;

    private String weibo;

    private String userAgreement;

    private String recruitTitle;

    private String recruitInfo;

    private String entryAgreement;

    private String blog;

    private String github;

    private static final long serialVersionUID = 1L;

}