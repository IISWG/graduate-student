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
 * 用户考研情况
 * </p>
 *
 * @author muxin
 * @since 2023-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_test_info")
@ApiModel(value="UserTestInfo对象", description="用户考研情况")
public class UserTestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    @ApiModelProperty(value = "考研年份")
    @TableField("time")
    private Integer time;

    @ApiModelProperty(value = "报考专业")
    @TableField("major")
    private String major;

    @ApiModelProperty(value = "报考院校")
    @TableField("university")
    private String university;

    @ApiModelProperty(value = "报考科目")
    @TableField("subject")
    private String subject;

    @ApiModelProperty(value = "院校目标")
    @TableField("cross_examination_situation")
    private String crossExaminationSituation;

    @ApiModelProperty(value = "备考状态")
    @TableField("test_state")
    private String testState;

    @ApiModelProperty(value = "当前状态")
    @TableField("present_state")
    private String presentState;

    @ApiModelProperty(value = "本科院校")
    @TableField("undergraduate_university")
    private String undergraduateUniversity;


}
