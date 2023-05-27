package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * praise
 * </p>
 *
 * @author muxin
 * @since 2023-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("praise")
@ApiModel(value="Praise对象", description="praise")
@Accessors(chain = true)
public class Praise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("article_id")
    private Long articleId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("is_praise")
    private Boolean isPraise;


}
