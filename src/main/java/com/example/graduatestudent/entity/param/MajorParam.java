package com.example.graduatestudent.entity.param;/**
 * @Author: muxin
 * @Date: 2023-04-11-14:33
 * @Description:
 */

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-11 14:33
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MajorParam implements Serializable {
    @TableId("major_code")
    private String major_code;
    @TableField("major_name")
    private String major_name;
    @TableField("parent_code")
    private String parent_id;
    @TableField("rank")
    private Integer rank;
    @TableField("major_type")
    private String major_type;
////    @Data
////    public class parent {
////        public String major_code;
////    }
//
//    parent parent;

}
