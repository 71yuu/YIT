package com.yuu.yit.education.course.service.common.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息
 *
 * @author Yuu
 */
@Data
@Accessors(chain = true)
public class CourseChapterBO implements Serializable {

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
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;
    /**
     * 1
     */
    private Integer sort;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 章节名称
     */
    private String chapterName;
    /**
     * 章节描述
     */
    private String chapterDesc;
    /**
     * 是否免费：1免费，0收费
     */
    private Integer isFree;
    /**
     * 原价
     */
    private BigDecimal chapterOriginal;
    /**
     * 优惠价
     */
    private BigDecimal chapterDiscount;
}
