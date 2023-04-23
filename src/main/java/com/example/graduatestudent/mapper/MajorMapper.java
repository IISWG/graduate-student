package com.example.graduatestudent.mapper;

import com.example.graduatestudent.entity.Major;
import com.example.graduatestudent.entity.param.SelectMajorListParam;
import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author muxin
 * @since 2023-04-10
 */
public interface MajorMapper extends MPJBaseMapper<Major> {
    public List<Major> getMajor(SelectMajorListParam selectMajorListParam);
    public Integer getMajorTotal(SelectMajorListParam selectMajorListParam);
}
