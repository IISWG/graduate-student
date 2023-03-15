package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 文章信息
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_information")
@ApiModel(value="ArticleInformation对象", description="文章信息")
public class ArticleInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "文章所属用户的id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "文章标题")
    @TableField("article_title")
    private String articleTitle;

    @ApiModelProperty(value = "文章类别")
    @TableField("article_type")
    private String articleType;

    @ApiModelProperty(value = "文章创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "文章最近一次修改时间")
    @TableField("latest_update_time")
    private LocalDateTime latestUpdateTime;

    @ApiModelProperty(value = "文章内容")
    @TableField("article_content")
    private String articleContent;


}
