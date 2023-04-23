package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.param.GetCollegeListGBAreaParam;
import com.example.graduatestudent.entity.param.SelectCollegeParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.service.IUniversityBasicInformationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 大学信息表 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/universityBasicInformation")
@Slf4j
public class UniversityBasicInformationController {
    @Resource
    IUniversityBasicInformationService universityBasicInformationService;

    @PostMapping("/getCollegeList")
    @ApiOperation(value = "通过学校的属性和搜索内容获得学校信息列表", notes = "")
    public BaseResult getCollegeList(@RequestBody SelectCollegeParam selectCollegeParam) {
        log.info("selectCollegeParam:{}",selectCollegeParam);
        PageResult serviceCollegeList = universityBasicInformationService.getCollegeList(selectCollegeParam);
        return new OkResult(serviceCollegeList);
    }

    @PostMapping("/getCollegeListGroupByArea")
    @ApiOperation(value = "", notes = "")
    public BaseResult getCollegeListGroupByArea(@RequestBody GetCollegeListGBAreaParam getMajorListGBCollegeParam) {
        log.info("getMajorListGBCollegeParam:{}", getMajorListGBCollegeParam);
        String content = getMajorListGBCollegeParam.getContent();
        if (content == null || content.equals("")) {
            getMajorListGBCollegeParam.setContent(null);
        }
        List<?> collegeListGroupByArea = universityBasicInformationService.getCollegeListGroupByArea(getMajorListGBCollegeParam);
        return new OkResult(collegeListGroupByArea);

    }
//    @PostMapping("/getCollegeListGroupByArea")
//    @ApiOperation(value = "", notes = "")
//    public BaseResult getCollegeListGroupByContent(@RequestBody GetCollegeListGBAreaParam getMajorListGBCollegeParam) {
//        log.info("getMajorListGBCollegeParam:{}", getMajorListGBCollegeParam);
//        universityBasicInformationService.list(new QueryWrapper<>().eq(""))
//        return new OkResult(collegeListGroupByArea);
//
//    }

}
