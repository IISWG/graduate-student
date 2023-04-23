package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("major_information")
@ApiModel(value="MajorInformation对象", description="")
public class MajorInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业代码")
    @TableId("professional_code")
    private String professionalCode;

    @ApiModelProperty(value = "专业名称")
    @TableField("professional_name")
    private String professionalName;

    @ApiModelProperty(value = "专业类型（学硕和专硕）")
    @TableField("major_type")
    private String majorType;

    @ApiModelProperty(value = "专业门类（如管理类，理学，工学）")
    @TableField("professional_category")
    private String professionalCategory;

    @ApiModelProperty(value = "专业简介")
    @TableField("introduction")
    private String introduction;


}
