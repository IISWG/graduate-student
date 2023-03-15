package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 大学信息表
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("university_basic_information")
@ApiModel(value="UniversityBasicInformation对象", description="大学信息表")
public class UniversityBasicInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "院校名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "所在地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_211")
    private Integer is211;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_985")
    private Integer is985;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_autonomous_marking")
    private Integer isAutonomousMarking;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_double_class")
    private Integer isDoubleClass;

    @ApiModelProperty(value = "学校类型")
    @TableField("school_type")
    private String schoolType;

    @ApiModelProperty(value = "所属管理")
    @TableField("belong_to")
    private String belongTo;


}
