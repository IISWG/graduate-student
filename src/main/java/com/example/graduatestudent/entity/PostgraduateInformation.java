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
 * 考研信息表
 * </p>
 *
 * @author muxin
 * @since 2023-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("postgraduate_information")
@ApiModel(value="PostgraduateInformation对象", description="考研信息表")
public class PostgraduateInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "考研年份")
    @TableField("time")
    private String time;

    @ApiModelProperty(value = "报考专业")
    @TableField("major")
    private String major;

    @ApiModelProperty(value = "报考院校")
    @TableField("university")
    private String university;

    @ApiModelProperty(value = "用户信息id")
    @TableField("user_id")
    private Long userId;


}
