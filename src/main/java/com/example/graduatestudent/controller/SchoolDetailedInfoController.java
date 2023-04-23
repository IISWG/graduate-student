package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.SchoolDetailedInfo;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.service.ISchoolDetailedInfoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 学校附加信息 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-04-12
 */
@RestController
@Slf4j
@RequestMapping("/schoolDetailedInfo")
public class SchoolDetailedInfoController {

    @Resource
    ISchoolDetailedInfoService schoolDetailedInfoService;
    @GetMapping("/getCollegeInfo")
    @ApiOperation(value = "通过学校的id获得学校的附加信息和院校信息", notes = "")
    public BaseResult getCollegeInfo(Integer id) {
        log.info("id:{}",id);
        SchoolDetailedInfo collegeInfo = schoolDetailedInfoService.getCollegeInfo(id);
        return new OkResult(collegeInfo);
    }


}
