package com.example.graduatestudent.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
 
/**
 * @description:
 * @author: muxin
 * @date: 2023/4/3 11:11
 * @param:
 * @return:
 **/

@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
 
    int seconds();
    int maxCount();
    boolean needLogin()default true;
}