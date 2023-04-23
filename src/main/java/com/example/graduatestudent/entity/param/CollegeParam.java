package com.example.graduatestudent.entity.param;/**
 * @Author: muxin
 * @Date: 2023-04-11-19:48
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-11 19:48
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CollegeParam implements Serializable {
    private Integer school_id;
    private String school_name;
    private Integer is_211;
    private Integer is_985;
    private Integer is_score;
    private Integer is_first_class;
    private Integer is_other;
    private Integer school_type;
    private Integer is_edu;
    private Integer is_local;
    private Integer is_center;
    private String province_name;
    private String student_recruitment;
}
