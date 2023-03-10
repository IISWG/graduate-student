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
 * 基本信息表
 * </p>
 *
 * @author muxin
 * @since 2023-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("basic_information")
@ApiModel(value="BasicInformation对象", description="基本信息表")
public class BasicInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "0：不跨考；1：跨专业；2：跨院校；3：跨专业和院校")
    @TableField("cross_examination_situation")
    private Integer crossExaminationSituation;

    @ApiModelProperty(value = "0：一战；1：二战；3：多战")
    @TableField("test_state")
    private Integer testState;

    @ApiModelProperty(value = "院校信息id")
    @TableField("undergraduate_university")
    private Integer undergraduateUniversity;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;


}
