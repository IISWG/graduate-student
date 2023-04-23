package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.Major;
import com.example.graduatestudent.entity.MajorLearningStyle;
import com.example.graduatestudent.entity.param.SelectMajorListParam;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.mapper.MajorLearningStyleMapper;
import com.example.graduatestudent.mapper.MajorMapper;
import com.example.graduatestudent.service.IMajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-04-10
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

    @Resource
    MajorMapper majorMapper;
    @Resource
    MajorLearningStyleMapper learningStyleMapper;

    @Override
    public PageResult getMajorList(SelectMajorListParam selectMajorListParam) {
        Integer pageSizeParam = selectMajorListParam.getPageSize();
        Integer pageNum = selectMajorListParam.getPageNum();
        selectMajorListParam.setPageSize(pageSizeParam * pageNum);
        selectMajorListParam.setPageNum(pageSizeParam * pageNum - pageSizeParam);
        Integer majorTotal = majorMapper.getMajorTotal(selectMajorListParam);
        List<Major> records = majorMapper.getMajor(selectMajorListParam);
        Integer pageSize = selectMajorListParam.getPageSize();
        Long page = Long.valueOf(majorTotal / pageSize);
        Long pages = majorTotal % pageSize == 0 ? page : page + 1;
        if (records.size() == 0) {
            return new PageResult((long) (majorTotal), pages, records);
        }
        for (Major record : records) {
            Major majorParent = majorMapper.selectById(record.getParentCode());
            majorParent.setParentInfo(majorMapper.selectById(majorParent.getParentCode()));
            record.setParentInfo(majorParent);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("major_code", record.getMajorCode());
            hashMap.put("is_full_time", selectMajorListParam.getFullTime());
            hashMap.put("english_number", selectMajorListParam.getEnglishNumber());
            hashMap.put("math_number", selectMajorListParam.getMathNumber());
            List<MajorLearningStyle> majorLearningStyles = learningStyleMapper.selectList(new QueryWrapper<MajorLearningStyle>()
                    .allEq(hashMap, false));
            ArrayList<Long> schoolIdList = new ArrayList<>();
            for (MajorLearningStyle majorLearningStyle : majorLearningStyles) {
                schoolIdList.add(majorLearningStyle.getUniversityId());
            }
            record.setSchoolIdList(schoolIdList);
        }
        return new PageResult((long) (majorTotal), pages, records);

    }
}
