package com.example.graduatestudent.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.graduatestudent.entity.param.CollegeParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * <p>
 * 大学信息表
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("university_basic_information")
@ApiModel(value = "UniversityBasicInformation对象", description = "大学信息表")
public class UniversityBasicInformation implements Serializable {

    public UniversityBasicInformation(CollegeParam collegeParam, HashMap<Integer, String> hashMap) {
        this.area = collegeParam.getProvince_name();
        Integer is_edu = collegeParam.getIs_edu();
        Integer is_local = collegeParam.getIs_local();
        Integer is_center = collegeParam.getIs_center();
        String bt = null;
        if (is_edu == 1) {
            bt = "教育部直属";
        }
        if (is_local == 1) {
            bt = "地方所属";
        }
        if (is_center == 1) {
            bt = "中央部委直属";
        }
        this.belongTo = bt;
        this.id = collegeParam.getSchool_id();
        this.is211 = collegeParam.getIs_211();
        this.is985 = collegeParam.getIs_985();
        this.isAutonomousMarking = collegeParam.getIs_score();
        this.isDoubleClass = collegeParam.getIs_first_class();
        this.isOrdinary = collegeParam.getIs_other();
        this.name = collegeParam.getSchool_name();
        this.schoolType = hashMap.get(collegeParam.getSchool_type());

    }

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Integer id;

    @ApiModelProperty(value = "院校名字")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "所在地区")
    @TableField("area")
    private String area;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_211")
    private Integer is211;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_985")
    private Integer is985;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_autonomous_marking")
    private Integer isAutonomousMarking;

    @ApiModelProperty(value = "0：不是；1：是")
    @TableField("is_double_class")
    private Integer isDoubleClass;

    @ApiModelProperty(value = "学校类型")
    @TableField("school_type")
    private String schoolType;

    @ApiModelProperty(value = "所属管理")
    @TableField("belong_to")
    private String belongTo;

    @ApiModelProperty(value = "是否是普通院校")
    @TableField("is_ordinary")
    private Integer isOrdinary;
    @ApiModelProperty(value = "图片")
    @TableField("picture")
    private String picture;


}
