package com.example.graduatestudent.entity.result;/**
 * @Author: muxin
 * @Date: 2023-04-14-16:34
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-14 16:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CollegeListGBAreaResult {
    private String area;
    private List<String> collegeNameList;
}
