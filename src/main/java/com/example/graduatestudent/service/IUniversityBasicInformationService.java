package com.example.graduatestudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduatestudent.entity.UniversityBasicInformation;
import com.example.graduatestudent.entity.param.GetCollegeListGBAreaParam;
import com.example.graduatestudent.entity.param.SelectCollegeParam;
import com.example.graduatestudent.entity.result.PageResult;

import java.util.List;

/**
 * <p>
 * 大学信息表 服务类
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
public interface IUniversityBasicInformationService extends IService<UniversityBasicInformation> {

    public PageResult getCollegeList(SelectCollegeParam selectCollegeParam);

    UniversityBasicInformation getCollegeInfo(Integer id);

    List<?> getCollegeListGroupByArea(GetCollegeListGBAreaParam param);
}
