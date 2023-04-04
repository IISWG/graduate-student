package com.example.graduatestudent.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.UserInformation;
import com.example.graduatestudent.entity.param.MailVerifyParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.IUserInformationService;
import com.example.graduatestudent.service.impl.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Resource
    IUserInformationService userInformationService;

    @Resource
    MailService mailService;

    @GetMapping("/sendEmail")
    @ResponseBody
    public BaseResult sendEmail(String email){
        System.out.println("email:"+email);
        boolean sendMimeMail = mailService.sendMimeMail(email);
        if (sendMimeMail) {
            return new OkResult("发送成功！","");
        } else {
            return new ServerErrResult("发送失败！");
        }
    }

    @PostMapping("/regist")
    @ResponseBody
    public BaseResult regist(@RequestBody MailVerifyParam userVo){
//        HttpSession session = request.getSession();
//        System.out.println("userVo:"+userVo);
        log.info("userVo:{}",userVo);
        BaseResult registered = mailService.registered(userVo);
        return registered;
    }
    @PostMapping("/changePassword")
    @ResponseBody
    public BaseResult changePassword(@RequestBody MailVerifyParam userVo){

        System.out.println("userVo:"+userVo);
        return mailService.changePassword(userVo);
    }
    @PostMapping("/changeName")
    @ResponseBody
    public BaseResult changeName(@RequestBody MailVerifyParam userVo){

        System.out.println("userVo:"+userVo);
        return mailService.changeName(userVo);
    }

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public BaseResult doLogin(@RequestBody UserInformation userInfo) {
        try {
            UserInformation userInformation = userInformationService.selectOneByEmail(userInfo.getEmail());
            if (userInformation == null) {
                userInformation = userInformationService.getOne(new QueryWrapper<UserInformation>().eq("telephone_number", userInfo.getEmail()));
            }
            // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
            if (userInformation == null) {
                return new ServerErrResult("当前邮箱没有注册，请先注册！");
            } else {
                if (userInformation.getPassword().equals(userInfo.getPassword())) {
                    StpUtil.login(userInfo.getEmail());
                    SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                    Object loginIdByToken = StpUtil.getLoginIdByToken(userInfo.getEmail());
                    log.info("userInfo.getEmail():{}", loginIdByToken);
                    userInformation.setPassword(null);
                    OkResult okResult = new OkResult("登录成功！", userInformation);
                    okResult.setToken(tokenInfo.getTokenValue());
                    return okResult;
                } else {
                    return new ServerErrResult("密码错误！");
                }
            }
        } catch (Exception e) {
            return new ServerErrResult("服务器错误！");
        }

    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin

    @RequestMapping("isLogin")
    public BaseResult isLogin() {
        String tokenValue = StpUtil.getTokenValue();
        log.info("tokenValue:{}",tokenValue);
        String tokenName = StpUtil.getTokenName();
        log.info("tokenName:{}",tokenName);
        long tokenTimeout = StpUtil.getTokenTimeout();
        log.info("tokenTimeout:{}",tokenTimeout);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        log.info("tokenInfo:{}",tokenInfo);
        Object loginId = StpUtil.getLoginIdDefaultNull();

        return new OkResult(StpUtil.isLogin()) ;
    }
    @RequestMapping("logout")
    public BaseResult logout() {
        StpUtil.logout();
        return new OkResult() ;
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
