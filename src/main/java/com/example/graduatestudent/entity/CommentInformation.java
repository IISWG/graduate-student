package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@ApiModel(value = "CommentInformation对象", description = "评论信息")
@Accessors(chain = true)
public class CommentInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论内容")
    @TableField("comment_content")
    private String commentContent;

    @ApiModelProperty(value = "评论文章id")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty(value = "评论与回复的时间")
    @TableField("comment_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    @ApiModelProperty(value = "评论人头像", name = "avatar")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty(value = "评论人名称", name = "nickname")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty(value = "对谁评论人的名称", name = "toPerName")
    @TableField(exist = false)
    private String toPerName;

    @ApiModelProperty(value = "评论人的邮箱", name = "email")
    @TableField(exist = false)
    private String email;

    @ApiModelProperty(value = "该评论下的回复", name = "commentInformations")
    @TableField(exist = false)
    private List<CommentInformation> commentInformations;
}
