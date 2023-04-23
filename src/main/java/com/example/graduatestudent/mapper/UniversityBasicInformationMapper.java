package com.example.graduatestudent.mapper;

import com.example.graduatestudent.entity.UniversityBasicInformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.graduatestudent.entity.param.GetCollegeListGBAreaParam;
import com.example.graduatestudent.entity.result.CollegeListGBAreaResult;

import java.util.List;

/**
 * <p>
 * 大学信息表 Mapper 接口
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
public interface UniversityBasicInformationMapper extends BaseMapper<UniversityBasicInformation> {
    List<CollegeListGBAreaResult> getCollegeListGroupByArea(GetCollegeListGBAreaParam param, String area);
}
