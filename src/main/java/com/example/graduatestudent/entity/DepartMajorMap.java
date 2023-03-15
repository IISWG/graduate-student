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
 * 院系与专业映射表
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("depart_major_map")
@ApiModel(value="DepartMajorMap对象", description="院系与专业映射表")
public class DepartMajorMap implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "院系id")
    @TableField("depart_id")
    private Long departId;

    @ApiModelProperty(value = "专业代码")
    @TableField("major_code")
    private String majorCode;


}
