package com.example.graduatestudent.entity.param;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-10 20:37
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SelectMajorListParam extends PageParam {
    private String majorCode;
    private String majorType;
    /**
     * 全日制或者非全日制,0：不是；1：是
     */
    private Integer fullTime;
    /**
     * 0：其他；1：英语1；2：英语2
     */
    private Integer englishNumber;
    /**
     * 0：其他；1：数学1；2：数学2；3：数学3
     */
    private Integer mathNumber;
    /**
     * 搜索的内容
     */
    private String content;
    private Integer schoolId;
}
