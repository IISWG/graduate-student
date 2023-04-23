package com.example.graduatestudent.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.*;
import com.example.graduatestudent.entity.param.CollegeParam;
import com.example.graduatestudent.entity.param.MajorParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.mapper.UniversityBasicInformationMapper;
import com.example.graduatestudent.service.IFacultyInfoService;
import com.example.graduatestudent.service.IMajorLearningStyleService;
import com.example.graduatestudent.service.ISchoolDetailedInfoService;
import com.example.graduatestudent.service.IUniversityTypeService;
import com.example.graduatestudent.service.impl.CrawlDataImpl;
import com.example.graduatestudent.util.HttpClientUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program: graduate-student
 * @description:
 * @author: muxin
 * @create: 2023-04-11 13:52
 **/
@RestController
@RequestMapping("/crawlData")
@Slf4j
public class CrawlDataController {

    @Resource
    CrawlDataImpl crawlData;
    @Resource
    HttpClientUtils httpClientUtils;
    @Resource
    UniversityBasicInformationMapper universityBasicInformationMapper;
    @Resource
    IUniversityTypeService universityTypeService;
    @Resource
    IFacultyInfoService facultyInfoService;
    @Resource
    ISchoolDetailedInfoService schoolDetailedInfoService;
    @Resource
    IMajorLearningStyleService majorLearningStyleService;

    @GetMapping("/api")
    @ApiOperation(value = "调用考研帮api", notes = "")
    public BaseResult getCollegeInfoByUrl(String url) {
        log.info("url:{}", url);
        String api = httpClientUtils.api(url);
        JSONObject jsonObject = JSON.parseObject(api);
        log.info("jsonObject:{}",jsonObject);
        return new OkResult(jsonObject);
    }

    /**
     * @description: 用于将爬取的专业信息数据保存到数据库
     * @author: muxin
     * @date: 2023/4/11 13:53
     * @param:
     * @return:
     **/

    @PostMapping("/crawlDataMajor")
    public BaseResult crawlDataMajor(@RequestBody List<MajorParam> majorParams) throws Exception {
        int conversion = crawlData.conversion(majorParams);
//        JSON.parseObject()
        return new OkResult(conversion);
    }

    @PostMapping("/college")
    public BaseResult college(@RequestBody String url) throws Exception {

        List<UniversityType> list = universityTypeService.list();
        HashMap<Integer, String> ha = new HashMap<>();
        for (UniversityType universityType : list) {
            ha.put(universityType.getId(), universityType.getTypeName());
        }
        String request = httpClientUtils.request(url, 10, 1);
        JSONObject jsonObject = JSON.parseObject(request).getJSONObject("result");
        log.info("jsonObject:{}", jsonObject.toJSONString());
        Integer bigInteger = jsonObject.getInteger("last_page");
        for (int i = 1; i <= bigInteger; i++) {
            String res = httpClientUtils.request(url, 10, i);
            JSONObject result = JSON.parseObject(res).getJSONObject("result");
            JSONArray data = result.getJSONArray("data");
            log.info("data:{}", data.toJSONString());
            for (int j = 0; j < data.size(); j++) {
                JSONObject college = data.getJSONObject(j);
                CollegeParam collegeParam = college.toJavaObject(CollegeParam.class);
                log.info("collegeParam:{}", collegeParam);
                UniversityBasicInformation universityBasicInformation = new UniversityBasicInformation(collegeParam, ha);
                log.info("universityBasicInformation:{}", universityBasicInformation);
                UniversityBasicInformation selectById = universityBasicInformationMapper.selectById(universityBasicInformation.getId());
                if (selectById == null) {
                    int insert = universityBasicInformationMapper.insert(universityBasicInformation);
                }

            }

        }
        return new OkResult();
    }

    @PostMapping("/xueyuan")
    public BaseResult xueyuan() throws Exception {
        String url = "https://api.qz100.com/api-harbor/school/info/";
//        String request = httpClientUtils.xueyuan(url, 1000);
//        log.info("request:{}", request);
        List<UniversityBasicInformation> informationList = universityBasicInformationMapper.selectList(
                new QueryWrapper<UniversityBasicInformation>()
                        .orderByAsc("id"));
        for (UniversityBasicInformation universityBasicInformation : informationList) {
            String request = httpClientUtils.xueyuan(url, universityBasicInformation.getId());
            JSONObject result = JSON.parseObject(request).getJSONObject("result");
            log.info("request:{}", request);
            JSONObject contact = result.getJSONObject("contact");
            JSONArray faculty = result.getJSONArray("faculty");
            if (contact != null) {
                SchoolDetailedInfo schoolDetailedInfo = contact.toJavaObject(SchoolDetailedInfo.class);
                SchoolDetailedInfo school = schoolDetailedInfoService.getById(schoolDetailedInfo.getSchoolId());
                if (school == null) {
                    schoolDetailedInfoService.save(schoolDetailedInfo);
                }
                List<FacultyInfo> facultyInfos = faculty.toJavaList(FacultyInfo.class);

                for (FacultyInfo facultyInfo : facultyInfos) {
                    FacultyInfo facultyInfoServiceById = facultyInfoService.getById(facultyInfo.getId());
                    if (facultyInfoServiceById == null) {
                        facultyInfo.setUniversityId(schoolDetailedInfo.getSchoolId());
                        facultyInfoService.save(facultyInfo);
                    }
                }

            }

        }
//        log.info("request:{}", request);
        return new OkResult();

    }

    @GetMapping("study")
    public BaseResult study(Integer schId){
        String url = "https://api.qz100.com/api-cpp/major/list?first_major=&second_major=&is_tech=-1&limit=1000&page=1";
        log.info("schId:{}",schId);
        List<UniversityBasicInformation> informationList = universityBasicInformationMapper.selectList(
                new QueryWrapper<UniversityBasicInformation>()
                        .orderByAsc("id").gt("id",schId));

        for (UniversityBasicInformation universityBasicInformation : informationList) {
            //0-1学习方式
            for (int i = 0; i < 2; i++) {
                //1-3外语类型
                for (int j = 1; j < 4; j++) {
                    //1-4数学类型
                    for (int k = 1; k < 5; k++) {
                        Integer id = universityBasicInformation.getId();
                        String study = httpClientUtils.study(url, id, i, j, k);
                        log.info("study:{}", study);
                        JSONObject result = JSON.parseObject(study).getJSONObject("result");
                        JSONArray data = result.getJSONArray("data");
                        log.info("data:{}",data.toJSONString());
                        List<MajorParam> majorParams = data.toJavaList(MajorParam.class);
                        for (MajorParam majorParam : majorParams) {
                            MajorLearningStyle one = majorLearningStyleService.getOne(
                                    new QueryWrapper<MajorLearningStyle>()
                                            .eq("university_id", id)
                                            .eq("major_code", majorParam.getMajor_code()));
                            if (one == null) {
                                MajorLearningStyle majorLearningStyle = new MajorLearningStyle(id.longValue(), majorParam.getMajor_code(), i, j, k);
                                boolean save = majorLearningStyleService.save(majorLearningStyle);
                            }
                        }

                    }
                }
            }

        }
        return new OkResult();

    }
}
