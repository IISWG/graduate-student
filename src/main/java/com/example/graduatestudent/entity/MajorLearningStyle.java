package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学校的专业学习方式信息
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@TableName("major_learning_style")
@ApiModel(value="MajorLearningStyle对象", description="学校的专业学习方式信息")
public class MajorLearningStyle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "院校id")
    @TableId("university_id")
    private Long universityId;

    @ApiModelProperty(value = "专业代码")
    @TableField("major_code")
    private String majorCode;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_full_time")
    private Integer isFullTime;

    @ApiModelProperty(value = "1：英语1；2：英语2；3：其他；")
    @TableField("english_number")
    private Integer englishNumber;

    @ApiModelProperty(value = "1：数学1；2：数学2；3：数学3；4：其他；")
    @TableField("math_number")
    private Integer mathNumber;


}
