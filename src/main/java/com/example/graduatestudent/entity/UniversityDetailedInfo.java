package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("university_detailed_info")
@ApiModel(value="UniversityDetailedInfo对象", description="")
public class UniversityDetailedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("university_id")
    private Long universityId;

    @ApiModelProperty(value = "院校官网地址")
    @TableField("official_website")
    private String officialWebsite;

    @ApiModelProperty(value = "招生简章地址")
    @TableField("admission_brochure")
    private String admissionBrochure;

    @ApiModelProperty(value = "学校论坛地址")
    @TableField("school_forum")
    private String schoolForum;

    @ApiModelProperty(value = "就业报告分析")
    @TableField("employment_report")
    private String employmentReport;


}
