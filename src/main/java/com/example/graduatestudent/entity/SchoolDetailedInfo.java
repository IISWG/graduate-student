package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 学校附加信息
 * </p>
 *
 * @author muxin
 * @since 2023-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("school_detailed_info")
@ApiModel(value="SchoolDetailedInfo对象", description="学校附加信息")
public class SchoolDetailedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("school_id")
    @JsonProperty("school_id")
    private Integer schoolId;

    @ApiModelProperty(value = "学校名")
    @TableField("school_name")
    @JsonProperty("school_name")
    private String schoolName;

    @ApiModelProperty(value = "学校官网网址")
    @TableField("school_url")
    @JsonProperty("school_url")
    private String schoolUrl;

    @ApiModelProperty(value = "学校邮箱")
    @TableField("school_email")
    @JsonProperty("school_email")
    private String schoolEmail;

    @ApiModelProperty(value = "学校电话")
    @TableField("school_tel")
    @JsonProperty("school_tel")
    private String schoolTel;

    @ApiModelProperty(value = "学校地址")
    @TableField("school_addr")
    @JsonProperty("school_addr")
    private String schoolAddr;

    @ApiModelProperty(value = "研招办电话")
    @TableField("admission_office_tel")
    @JsonProperty("admission_office_tel")
    private String admissionOfficeTel;

    @ApiModelProperty(value = "研招办邮箱")
    @TableField("admission_office_email")
    @JsonProperty("admission_office_email")
    private String admissionOfficeEmail;

    @ApiModelProperty(value = "研招办网址")
    @TableField("admission_office_url")
    @JsonProperty("admission_office_url")
    private String admissionOfficeUrl;

    @TableField(exist = false)
    private List<FacultyInfo> facultyInfos;

}
