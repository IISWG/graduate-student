package com.example.graduatestudent.entity.result;

import com.example.graduatestudent.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-18 11:00
 **/
@Data
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class MajorTypeResult {
    private List<Major> majorFirst;
    private List<List<Major>> majorSecond;
}
