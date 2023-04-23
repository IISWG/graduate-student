package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.FacultyInfo;
import com.example.graduatestudent.entity.SchoolDetailedInfo;
import com.example.graduatestudent.mapper.FacultyInfoMapper;
import com.example.graduatestudent.mapper.SchoolDetailedInfoMapper;
import com.example.graduatestudent.service.ISchoolDetailedInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 学校附加信息 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-04-12
 */
@Service
public class SchoolDetailedInfoServiceImpl extends ServiceImpl<SchoolDetailedInfoMapper, SchoolDetailedInfo> implements ISchoolDetailedInfoService {

    @Resource
    SchoolDetailedInfoMapper schoolDetailedInfoMapper;
    @Resource
    FacultyInfoMapper facultyInfoMapper;
    @Override
    public SchoolDetailedInfo getCollegeInfo(Integer id) {
        SchoolDetailedInfo schoolDetailedInfo = schoolDetailedInfoMapper.selectById(id);
        List<FacultyInfo> facultyInfos = facultyInfoMapper.selectList(new QueryWrapper<FacultyInfo>()
                .eq("university_id", id));
        schoolDetailedInfo.setFacultyInfos(facultyInfos);
        return schoolDetailedInfo;
    }
}
