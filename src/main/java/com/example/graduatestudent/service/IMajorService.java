package com.example.graduatestudent.service;

import com.example.graduatestudent.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduatestudent.entity.param.SelectMajorListParam;
import com.example.graduatestudent.entity.result.PageResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muxin
 * @since 2023-04-10
 */
public interface IMajorService extends IService<Major> {

    PageResult getMajorList(SelectMajorListParam selectMajorListParam);
}
