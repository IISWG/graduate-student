package com.example.graduatestudent.entity.param;/**
 * @Author: muxin
 * @Date: 2023-03-27-17:16
 * @Description:
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @program: graduate-student
 * @description: 查询文章所需参数
 * @author: muxin
 * @create: 2023-03-27 17:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SelectArticleParam extends PageParam{
    @ApiModelProperty(value = "文章所属用户的id")
    private Long userId;

    private String type;
}
