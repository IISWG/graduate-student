package com.example.graduatestudent.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-10 14:06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SelectCollegeParam extends PageParam {
    private String area;
    private String attribute;
    private String type;
    private String toBelong;
    private String content;
}
