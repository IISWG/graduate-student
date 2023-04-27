package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.Major;
import com.example.graduatestudent.entity.MajorLearningStyle;
import com.example.graduatestudent.entity.Province;
import com.example.graduatestudent.entity.UniversityBasicInformation;
import com.example.graduatestudent.entity.param.GetCollegeListGBAreaParam;
import com.example.graduatestudent.entity.param.SelectCollegeParam;
import com.example.graduatestudent.entity.result.CollegeListGBAreaResult;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.mapper.MajorLearningStyleMapper;
import com.example.graduatestudent.mapper.MajorMapper;
import com.example.graduatestudent.mapper.ProvinceMapper;
import com.example.graduatestudent.mapper.UniversityBasicInformationMapper;
import com.example.graduatestudent.service.IUniversityBasicInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 大学信息表 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Service
@Slf4j
public class UniversityBasicInformationServiceImpl extends ServiceImpl<UniversityBasicInformationMapper, UniversityBasicInformation> implements IUniversityBasicInformationService {

    @Resource
    UniversityBasicInformationMapper universityBasicInformationMapper;

    @Resource
    MajorLearningStyleMapper majorLearningStyleMapper;
    @Resource
    ProvinceMapper provinceMapper;
    @Resource
    MajorMapper majorMapper;

    @Override
    public PageResult getCollegeList(SelectCollegeParam selectCollegeParam) {
        PageResult pageResult = new PageResult();
        Page<UniversityBasicInformation> universityBasicInformationPage = new Page<>(selectCollegeParam.getPageNum(), selectCollegeParam.getPageSize());
        QueryWrapper<UniversityBasicInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(selectCollegeParam.getArea() != null, "area", selectCollegeParam.getArea());
        queryWrapper.eq(selectCollegeParam.getType() != null, "school_type", selectCollegeParam.getType());
        String attribute = selectCollegeParam.getAttribute();
        if (attribute != null) {
            switch (attribute) {
                case "985": {
                    queryWrapper.eq("is_985", 1);
                    break;
                }
                case "211": {
                    queryWrapper.eq("is_211", 1);
                    break;
                }
                case "自主划线": {
                    queryWrapper.eq("is_autonomous_marking", 1);
                    break;
                }
                case "双一流": {
                    queryWrapper.eq("is_double_class", 1);
                    break;
                }
                case "普通院校": {
                    queryWrapper.eq("is_ordinary", 1);
                    break;
                }

            }
        }
        queryWrapper.eq(selectCollegeParam.getToBelong() != null, "belong_to", selectCollegeParam.getToBelong());
        queryWrapper.like(selectCollegeParam.getContent() != null, "name", "%" + selectCollegeParam.getContent() + "%");
        Page<UniversityBasicInformation> selectPage = universityBasicInformationMapper.selectPage(universityBasicInformationPage, queryWrapper);
        log.info("pages:" + selectPage.getPages());
        log.info("total:" + selectPage.getTotal());
        pageResult.setPages(selectPage.getPages());
        pageResult.setTotal(selectPage.getTotal());
        pageResult.setRecords(selectPage.getRecords());
        return pageResult;
    }

    @Override
    public UniversityBasicInformation getCollegeInfo(Integer id) {
        return null;
    }

    @Override
    public List<?> getCollegeListGroupByArea(GetCollegeListGBAreaParam param) {
        List<Province> provinces = provinceMapper.selectList(new QueryWrapper<Province>());
        ArrayList<CollegeListGBAreaResult> collegeListGBAreaResults = new ArrayList<>();
        HashMap<String, CollegeListGBAreaResult> hashMap = new HashMap<>();
        if (param.getMajorName() != null) {
            String[] s = param.getMajorName().split(" ");
            Major major = majorMapper.selectOne(new QueryWrapper<Major>()
                    .eq("major_code", s[0]));

            List<MajorLearningStyle> collegeId = majorLearningStyleMapper.selectList(
                    new QueryWrapper<MajorLearningStyle>()
                            .eq(major != null, "major_code", major.getMajorCode()));
            if(param.getContent() != null){
                ArrayList<String> collegeNameList = new ArrayList<>();
                for (MajorLearningStyle majorLearningStyle : collegeId) {
                    //获得满足条件的学校信息
                    List<UniversityBasicInformation> universityBasicInformations = universityBasicInformationMapper.selectList(
                            new QueryWrapper<UniversityBasicInformation>()
                                    .eq("id", majorLearningStyle.getUniversityId())
                                    .like("name", param.getContent()));
                    for (UniversityBasicInformation universityBasicInformation : universityBasicInformations) {
                        collegeNameList.add(universityBasicInformation.getName());
                    }
                }
                return collegeNameList;
            }
            for (MajorLearningStyle majorLearningStyle : collegeId) {
                //获得满足条件的学校信息
                UniversityBasicInformation university = universityBasicInformationMapper.selectById(majorLearningStyle.getUniversityId());
                //从hash中获得该地区的所有学校名
                CollegeListGBAreaResult orDefault = hashMap.getOrDefault(university.getArea(), new CollegeListGBAreaResult(university.getArea(), new ArrayList<>()));
                //将该学加入到对应的地区列表里
                orDefault.getCollegeNameList().add(university.getName());
                hashMap.put(university.getArea(), orDefault);
            }
        } else {

            List<UniversityBasicInformation> universityBasicInformations = universityBasicInformationMapper.selectList(
                    new QueryWrapper<UniversityBasicInformation>()
                            .like(param.getContent() != null, "name", param.getContent()));
            if(param.getContent() != null){
                ArrayList<String> collegeNameList = new ArrayList<>();
                for (UniversityBasicInformation universityBasicInformation : universityBasicInformations) {
                    collegeNameList.add(universityBasicInformation.getName());
                }
                return collegeNameList;
            }
            for (UniversityBasicInformation university : universityBasicInformations) {
                //从hash中获得该地区的所有学校名
                CollegeListGBAreaResult orDefault = hashMap.getOrDefault(university.getArea(), new CollegeListGBAreaResult(university.getArea(), new ArrayList<>()));
                //将该学加入到对应的地区列表里
                orDefault.getCollegeNameList().add(university.getName());
                hashMap.put(university.getArea(), orDefault);
            }
        }
        for (Province province : provinces) {
            CollegeListGBAreaResult collegeListGBAreaResult = hashMap.get(province.getProvinceName());
            if ( collegeListGBAreaResult != null) {
                collegeListGBAreaResults.add(collegeListGBAreaResult);
            }

        }
        return collegeListGBAreaResults;
    }
}
