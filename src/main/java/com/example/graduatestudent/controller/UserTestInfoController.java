package com.example.graduatestudent.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.graduatestudent.entity.UserTestInfo;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.service.IUserTestInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户考研情况 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/userTestInfo")
public class UserTestInfoController {
    @Resource
    IUserTestInfoService userTestInfoService;
    @PostMapping("/update")
    @ResponseBody
    public BaseResult update(@RequestBody UserTestInfo userTestInfo) {
        boolean save = userTestInfoService.update(new UpdateWrapper<UserTestInfo>()
                .set(userTestInfo.getTime() != null, "time", userTestInfo.getTime())
                .set(userTestInfo.getMajor() != null, "major", userTestInfo.getMajor())
                .set(userTestInfo.getUniversity() != null, "university", userTestInfo.getUniversity())
                .set(userTestInfo.getSubject() != null, "subject", userTestInfo.getSubject())
                .set(userTestInfo.getCrossExaminationSituation() != null, "cross_examination_situation", userTestInfo.getCrossExaminationSituation())
                .set(userTestInfo.getTestState() != null, "test_state", userTestInfo.getTestState())
                .set(userTestInfo.getPresentState() != null, "present_state", userTestInfo.getPresentState())
                .set(userTestInfo.getUndergraduateUniversity() != null, "undergraduate_university", userTestInfo.getUndergraduateUniversity())
                .eq("id", userTestInfo.getId()));
        if (save) {
            return new OkResult("更新成功！");
        } else {
            return new BaseResult(401, "更新失败！");
        }

    }

    @GetMapping("/getById")
    @ResponseBody
    public BaseResult getById(Long id) {
        UserTestInfo userTestInfo = userTestInfoService.getById(id);
        return new OkResult(userTestInfo);

    }
}
