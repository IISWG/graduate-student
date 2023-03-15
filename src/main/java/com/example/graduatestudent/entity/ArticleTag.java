package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章标签表
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_tag")
@ApiModel(value="ArticleTag对象", description="文章标签表")
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "标签名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "被引用数")
    @TableField("number")
    private Integer number;


}
