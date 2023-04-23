package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 关注信息表
 * </p>
 *
 * @author muxin
 * @since 2023-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("attention")
@ApiModel(value="Attention对象", description="关注信息表")
@Accessors(chain = true)
public class Attention implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "被关注用户的id")
    @TableField("focus_user_id")
    private Long focusUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "0:是取消关注，1：是关注")
    @TableField("is_attention")
    private String isAttention = "1";

    @ApiModelProperty(value = "被关注人用户信息")
    @TableField(exist = false)
    private UserInformation focusUserInfo;


}
