package com.example.graduatestudent.entity.param;/**
 * @Author: muxin
 * @Date: 2023-04-26-15:39
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-26 15:39
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SelectArticlCirleParam extends PageParam {
    private String content;
    private String majorCode;
    private String schoolId;
}
