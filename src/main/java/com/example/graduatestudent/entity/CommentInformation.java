package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论信息
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment_information")
@ApiModel(value="CommentInformation对象", description="评论信息")
public class CommentInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论内容")
    @TableField("comment_content")
    private String commentContent;

    @ApiModelProperty(value = "评论与回复的时间")
    @TableField("comment_time")
    private LocalDateTime commentTime;

    @ApiModelProperty(value = "评论回复的用户id")
    @TableField("comment_user_id")
    private Long commentUserId;

    @ApiModelProperty(value = "对那条评论回复回复id")
    @TableField("comment_id")
    private Long commentId;

    @ApiModelProperty(value = "对谁评论回复")
    @TableField("to_id")
    private Long toId;

    @ApiModelProperty(value = "是评论还是回复（1表示评论，2表示回复）")
    @TableField("comment_level")
    private Integer commentLevel;


}
