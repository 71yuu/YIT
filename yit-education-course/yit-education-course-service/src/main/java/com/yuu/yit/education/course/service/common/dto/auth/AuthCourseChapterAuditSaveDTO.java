package com.yuu.yit.education.course.service.common.dto.auth;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class AuthCourseChapterAuditSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节ID
     */
    @ApiModelProperty(value = "章节ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
    /**
     * 章节名称
     */
    @ApiModelProperty(value = "章节名称")
    private String chapterName;
    /**
     * 是否免费：1免费，0收费
     */
    @ApiModelProperty(value = "是否免费：1免费，0收费")
    private Integer isFree;
    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private BigDecimal chapterOriginal;
    /**
     * 优惠价
     */
    @ApiModelProperty(value = "优惠价")
    private BigDecimal chapterDiscount;
    
}
