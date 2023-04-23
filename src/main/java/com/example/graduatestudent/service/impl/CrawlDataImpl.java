package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.Major;
import com.example.graduatestudent.entity.param.MajorParam;
import com.example.graduatestudent.mapper.MajorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-11 13:49
 **/

@Service
public class CrawlDataImpl {
    @Resource
    MajorMapper majorMapper;
    @Transactional(rollbackFor = Exception.class)
    public int conversion(List<MajorParam> majorParams) throws Exception {
        int num = 0;
        for (MajorParam majorParam : majorParams) {
            Major major = new Major(majorParam);
            int insert = majorMapper.selectCount(new QueryWrapper<Major>()
                    .eq("major_code",majorParam.getMajor_code()));
            if (insert == 0) {
                int i = majorMapper.insert(major);
                if (i != 0) {
                    num++;
                }
            }

        }
        return num;


    }
}
