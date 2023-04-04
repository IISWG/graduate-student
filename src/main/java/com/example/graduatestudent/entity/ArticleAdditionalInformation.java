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
 * 文章的附加信息表
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_additional_information")
@ApiModel(value="ArticleAdditionalInformation对象", description="文章的附加信息表")
public class ArticleAdditionalInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章id")
    @TableId("article_id")
    private Long articleId;

    @ApiModelProperty(value = "收藏数")
    @TableField("collection_number")
    private Integer collectionNumber;

    @ApiModelProperty(value = "点赞数")
    @TableField("likes_number")
    private Integer likesNumber;

    @ApiModelProperty(value = "点击数")
    @TableField("hits_number")
    private Integer hitsNumber;


}
