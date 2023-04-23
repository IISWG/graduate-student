package com.example.graduatestudent.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-14 16:20
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetCollegeListGBAreaParam {
    /**
     * 搜索内容
     */
    private String content;
    private String majorName;
}
