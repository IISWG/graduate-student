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

/**
 * <p>
 * 
 * </p>
 *
 * @author muxin
 * @since 2023-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("faculty_info")
@ApiModel(value="FacultyInfo对象", description="")
public class FacultyInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "学院名")
    @TableField("faculty_name")
    @JsonProperty("faculty_name")
    private String facultyName;

    @ApiModelProperty(value = "学院电话")
    @TableField("faculty_tel")
    @JsonProperty("faculty_tel")
    private String facultyTel;

    @ApiModelProperty(value = "学院邮箱")
    @TableField("faculty_email")
    @JsonProperty("faculty_email")
    private String facultyEmail;

    @ApiModelProperty(value = "学院地址")
    @TableField("faculty_url")
    @JsonProperty("faculty_url")
    private String facultyUrl;

    @ApiModelProperty(value = "学校id")
    @TableField("university_id")
    @JsonProperty("university_id")
    private Integer universityId;


}
