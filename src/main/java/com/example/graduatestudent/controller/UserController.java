package com.example.graduatestudent.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.graduatestudent.entity.UserInformation;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.IUserInformationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/")
public class UserController {
    @Resource
    IUserInformationService userInformationService;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public BaseResult doLogin(String email, String password) {
        UserInformation userInformation = userInformationService.selectOneByEmail(email);
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对 
        if (userInformation == null) {
            return new ServerErrResult("当前邮箱没有注册，请先注册！");
        } else {
            if (userInformation.getPassword().equals(password)) {
                StpUtil.login(email);
                return new OkResult();
            } else {
                return new ServerErrResult("密码错误！");
            }
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin

    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @PostMapping("enroll")
    @ResponseBody
    public BaseResult enroll(@RequestBody UserInformation userInformation) {
        boolean save = userInformationService.save(userInformation);
        if (save) {
            return new OkResult();
        } else {
            return new BaseResult(401, "注册失败！");
        }

    }
}
