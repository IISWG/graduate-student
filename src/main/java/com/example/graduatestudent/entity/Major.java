package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.graduatestudent.entity.param.MajorParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author muxin
 * @since 2023-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("major")
@ApiModel(value="Major对象", description="")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Major implements Serializable {
    public Major(MajorParam majorParam) {
        this.majorCode = majorParam.getMajor_code();
        this.majorName = majorParam.getMajor_name();
        this.majorType = majorParam.getMajor_type();
        this.rankNum = majorParam.getRank();
//        if (majorParam.getParent_id() == null) {
//            this.parentCode = majorParam.getParent().major_code;
//        } else {
//            this.parentCode = majorParam.getParent_id();
//        }

    }

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业代码")
    @TableId("major_code")
    private String majorCode;

    @ApiModelProperty(value = "专业层级，1是最大层级，2是中间层级，3是最小的具体专业")
    @TableField("rank_num")
    private Integer rankNum;

    @ApiModelProperty(value = "专业名称")
    @TableField("major_name")
    private String majorName;

    @ApiModelProperty(value = "上级的专业代码，顶级的层级的上级专业代码为1000")
    @TableField("parent_code")
    private String parentCode;

    @TableField("major_type")
    private String majorType;

    @ApiModelProperty(value = "上一级专业信息", name = "parentInfo")
    @TableField(exist = false)
    private Major parentInfo;

    @ApiModelProperty(value = "该专业开设的院校数量", name = "schoolNum")
    @TableField(exist = false)
    private Integer schoolNum;
    @ApiModelProperty(value = "该专业开设的信息", name = "schoolIdList")
    @TableField(exist = false)
    private List<Long> schoolIdList;

}
