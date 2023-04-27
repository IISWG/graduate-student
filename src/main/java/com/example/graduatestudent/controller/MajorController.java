package com.example.graduatestudent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.Major;
import com.example.graduatestudent.entity.param.SelectMajorListParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.MajorTypeResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.service.IMajorService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-04-10
 */
@RestController
@Slf4j
@RequestMapping("/major")
public class MajorController {
    @Resource
    IMajorService majorService;

    @PostMapping("/getMajorList")
    @ApiOperation(value = "通过专业的属性和搜索内容获得专业信息列表", notes = "")
    public BaseResult getMajorList(@RequestBody SelectMajorListParam selectMajorListParam) {
        log.info("selectMajorListParam:{}", selectMajorListParam);
        String majorType = selectMajorListParam.getMajorType();
        String majorCode = selectMajorListParam.getMajorCode();
        char[] chars = majorCode.toCharArray();
        if (majorType != null) {
            if (majorType.equals("学术硕士")) {
                chars[0] = '1';
            } else {
                chars[0] = '2';
            }
        } else {
            chars[0] = '_';
        }
        selectMajorListParam.setMajorCode(String.copyValueOf(chars));
        PageResult majorList = majorService.getMajorList(selectMajorListParam);
        return new OkResult(majorList);

    }

    @GetMapping("/getMajor")
    @ApiOperation(value = "获得专业分类", notes = "")
    public BaseResult getMajor() {
        List<Major> majorFirst = majorService.list(new QueryWrapper<Major>()
                .eq("rank_num", 1)
                .likeRight("major_code", 1));
        ArrayList<List<Major>> majorSecond = new ArrayList<>();
        for (Major major : majorFirst) {
            List<Major> list = majorService.list(new QueryWrapper<Major>()
                    .eq("rank_num", 2)
                    .likeRight("parent_code", major.getMajorCode().replaceFirst("1", "_")));
            majorSecond.add(list);
        }
        MajorTypeResult majorTypeResult = new MajorTypeResult(majorFirst, majorSecond);
        return new OkResult(majorTypeResult);

    }

    @GetMapping("/getMajorListByType")
    @ApiOperation(value = "", notes = "")
    public BaseResult getMajorListByType() {
        List<Major> academicList = majorService.list(new QueryWrapper<Major>()
                .eq("rank_num", 1)
                .likeRight("major_code", 1));
        List<Major> professionalList = majorService.list(new QueryWrapper<Major>()
                .eq("rank_num", 1)
                .likeRight("major_code", 2));
        list(academicList);
        list(professionalList);
        ArrayList<List<Major>> lists = new ArrayList<>();
        lists.add(academicList);
        lists.add(professionalList);
        return new OkResult(lists);

    }

    private void list(List<Major> professionalList) {
        for (Major major : professionalList) {
            List<Major> majors = majorService.list(new QueryWrapper<Major>()
                    .eq("parent_code", major.getMajorCode()));
            for (Major m : majors) {
                List<Major> ms = majorService.list(new QueryWrapper<Major>()
                        .eq("parent_code", m.getMajorCode()));
                m.setChildMajorList(ms);
            }
            major.setChildMajorList(majors);
        }
    }


}
