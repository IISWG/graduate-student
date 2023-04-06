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
 * 文章收藏表
 * </p>
 *
 * @author muxin
 * @since 2023-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("collect_article")
@ApiModel(value="CollectArticle对象", description="文章收藏表")
public class CollectArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "文章id")
    @TableField("article_id")
    private Long articleId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "0是未收藏，1是已收藏")
    @TableField("is_collect")
    private Boolean isCollect;


}
