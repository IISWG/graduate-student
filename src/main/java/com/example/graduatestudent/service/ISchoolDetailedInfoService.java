package com.example.graduatestudent.service;

import com.example.graduatestudent.entity.SchoolDetailedInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学校附加信息 服务类
 * </p>
 *
 * @author muxin
 * @since 2023-04-12
 */
public interface ISchoolDetailedInfoService extends IService<SchoolDetailedInfo> {

    SchoolDetailedInfo getCollegeInfo(Integer id);
}
