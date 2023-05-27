package com.example.graduatestudent.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.UserInformation;
import com.example.graduatestudent.entity.UserTestInfo;
import com.example.graduatestudent.entity.param.MailVerifyParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.mapper.UserInformationMapper;
import com.example.graduatestudent.mapper.UserTestInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Slf4j
public class MailService {
    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired

    @Resource
    private UserInformationMapper blogPersonalInformMapper;
    @Resource
    private UserTestInfoMapper userTestInfoMapper;

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String from;

    /**
     * 给前端输入的邮箱，发送验证码
     * @param email
     * @param
     * @return
     */
    public boolean sendMimeMail(String email) {
        try {
//            HttpSession session = httpServletRequest.getSession();
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String code = randomCode();
            System.out.println("code:"+code);
            mailMessage.setText("您收到的验证码是："+code);//内容

            mailMessage.setTo(email);//发给谁

            mailMessage.setFrom(from);//你自己的邮箱

            mailSender.send(mailMessage);//发送
            redisTemplate.opsForValue().set(email, code,  60 * 10, TimeUnit.SECONDS);

            //将随机数放置到session中
//            session.setAttribute(email,email);
//            session.setAttribute(email+"code",code);
//            session.setMaxInactiveInterval(6000*1000);
//            Cookie cookie = new Cookie("JSESSIONID", session.getId());
//            cookie.setMaxAge(60*60*24);
//            httpServletResponse.addCookie(cookie);
//            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie跨域

            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 随机生成6位数的验证码
     * @return String code
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 检验验证码是否一致
     * @param
     * @param
     * @return
     */
    @Transactional
    public BaseResult registered(MailVerifyParam mailVerifyParam){
        log.info("mailVerifyParam:{}",mailVerifyParam);
        //获取表单中的提交的验证信息
        String voCode = mailVerifyParam.getCode();
        String mail = mailVerifyParam.getEmail();

        String code = redisTemplate.opsForValue().get(mail);
        System.out.println("getredis_code:"+code);
        UserInformation personalInform = blogPersonalInformMapper.selectOne(new QueryWrapper<UserInformation>().eq("email", mail));

        if (personalInform != null) {
            log.error("该邮箱已被注册！");
            return new ServerErrResult("该邮箱已被注册！");
        }
        //如果email数据为空，或者不一致，注册失败
        if (code == null || code.isEmpty()){
            System.out.println("code:"+code);
            log.error("验证码失效或没发验证码！");
            return new ServerErrResult("验证码失效或没发验证码！");
        }else if (!code.equals(voCode)){
            log.error("验证码错误！");
            return new ServerErrResult("验证码错误！");
        }

        UserInformation blogPersonalInform = new UserInformation();
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        blogPersonalInform.setEmail(mailVerifyParam.getEmail())
                .setPassword(mailVerifyParam.getPassword())
                .setNickname(mailVerifyParam.getEmail()+"graduate");
        int insert = blogPersonalInformMapper.insert(blogPersonalInform);
        int insertUserTestInfo = 1;
        if (insert != 0) {
            UserInformation userInformation = blogPersonalInformMapper.selectOneByEmail(blogPersonalInform.getEmail());
            UserTestInfo userTestInfo = new UserTestInfo();
            userTestInfo.setId(userInformation.getId());
            insertUserTestInfo = userTestInfoMapper.insert(userTestInfo);
        }
        if (insert == 0 || insertUserTestInfo == 0) {
            log.error("注册失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult("注册失败！");
        }
        //跳转成功页面
        return new OkResult("注册成功！","");
    }

   public BaseResult changePassword(@NotNull MailVerifyParam mailVerifyParam){
       String voCode = mailVerifyParam.getCode();
       String mail = mailVerifyParam.getEmail();
       String password = mailVerifyParam.getPassword();
       String oldPassword = mailVerifyParam.getOldpassword();
       //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       try {
//           if (oldPassword != null && !oldPassword.equals("")) {
////               String encode = bCryptPasswordEncoder.encode(oldpassword);
////               System.out.println("encode:"+encode);
//               UserInformation blogPersonalInform = blogPersonalInformMapper.selectOne(new QueryWrapper<UserInformation>().eq("email", mail));
//               System.out.println("UserInformation:"+blogPersonalInform);
//               if (blogPersonalInform != null) {
////                   if (bCryptPasswordEncoder.matches(oldpassword, blogPersonalInform.getPassword())) {
//                   if (oldPassword.equals(password)) {
////                       blogPersonalInform.setPassword(bCryptPasswordEncoder.encode(password))
//                       blogPersonalInform.setPassword(password);
////                                          .setUpdateTime(LocalDateTime.now());
//                       int updateById = blogPersonalInformMapper.updateById(blogPersonalInform);
//                       if (updateById == 0) {
//                           log.error("修改密码失败！");
//                           return new ServerErrResult("修改密码失败！");
//                       }
//                       //跳转成功页面
//                       return new OkResult("修改密码成功！", "");
//                   }
//                   else {
//                       return new ServerErrResult("旧密码输入错误！");
//                   }
//
//               } else {
//                   return new ServerErrResult("账号问题！");
//               }
//           }

           String code = redisTemplate.opsForValue().get(mail);
           System.out.println("getredis_code:" + code);
           UserInformation personalInform = blogPersonalInformMapper.selectOne(new QueryWrapper<UserInformation>().eq("email", mail));
           if (personalInform == null) {
               log.error("该邮箱没被注册！");
               return new ServerErrResult("该邮箱没被注册！请先注册！");
           }
           //如果email数据为空，或者不一致，注册失败
           if (code == null || code.isEmpty()) {
               System.out.println("code:" + code);
               log.error("验证码失效或没发验证码！");
               return new ServerErrResult("验证码失效或没发验证码！");
           } else if (!code.equals(voCode)) {
               //return "error,请重新注册";
               log.error("验证码错误！");
               return new ServerErrResult("验证码错误！");
           }

           personalInform.setPassword(password);
           int updateById = blogPersonalInformMapper.updateById(personalInform);
           if (updateById == 0) {
               log.error("修改密码失败！");
               return new ServerErrResult("修改密码失败！");
           }
           //跳转成功页面
           return new OkResult("修改密码成功！", "");
       } catch (Exception e) {
           log.error(e.getMessage());
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return new ServerErrResult("修改密码失败！");
       }

    }
   public BaseResult changeName(MailVerifyParam mailVerifyParam){

       String mail = mailVerifyParam.getEmail();
       String newname = mailVerifyParam.getNewname();

       try {
           UserInformation blogPersonalInform = blogPersonalInformMapper.selectOne(new QueryWrapper<UserInformation>().eq("email", mail));
           blogPersonalInform.setNickname(newname);
           int updateById = blogPersonalInformMapper.updateById(blogPersonalInform);
           return new OkResult("修改昵称成功！", blogPersonalInform);
       } catch (Exception e) {
           log.error(e.getMessage());
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
           return new ServerErrResult("修改昵称失败！");
       }

    }
}
