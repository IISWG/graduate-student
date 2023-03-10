package com.example.graduatestudent;

import cn.dev33.satoken.SaManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@MapperScan("com.example.graduatestudent.mapper")
@EntityScan("com.example.graduatestudent.entity")
public class GraduateStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateStudentApplication.class, args);
        System.out.println("启动成功：Sa-Token配置如下：" + SaManager.getConfig());
    }

}
