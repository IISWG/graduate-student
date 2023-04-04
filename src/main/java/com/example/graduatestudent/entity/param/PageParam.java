package com.example.graduatestudent.entity.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author muxin
 * @date
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageParam {

    /**
     * 每页的大小
     */
    @ApiModelProperty(value = "分页规格(默认为10)")
    private Integer pageSize = 10;

    /**
     *分页第几页
     */
    @ApiModelProperty(value = "分页页码(默认为1)")
    private Integer pageNum = 1;
}
