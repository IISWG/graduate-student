package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章和标签的关联表
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tag_map")
@ApiModel(value="TagMap对象", description="文章和标签的关联表")
public class TagMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("article_id")
    private Long articleId;

    @TableField("tag_id")
    private Integer tagId;


}
