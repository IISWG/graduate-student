//package com.example.graduatestudent.config;
//
//import cn.dev33.satoken.interceptor.SaInterceptor;
//import cn.dev33.satoken.router.SaRouter;
//import cn.dev33.satoken.stp.StpUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@Slf4j
//public class SaTokenConfigure implements WebMvcConfigurer {
//    // 注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
//        registry.addInterceptor(new SaInterceptor(handle -> {
//            SaRouter
//                    .match("/**")    // 拦截的 path 列表，可以写多个 */
//                    .notMatch("/user/**", "/ImgController/**")        // 排除掉的 path 列表，可以写多个
//                    .check(r -> StpUtil.checkLogin());
//            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//        })).addPathPatterns("/**");
//
//
//    }
//
//    /**
//     * 注册 [Sa-Token全局过滤器]
//     */
////    @Bean
////    public SaServletFilter getSaServletFilter() {
////        return new SaServletFilter()
////
////                // 指定 拦截路由 与 放行路由
////                .addInclude("/**").addExclude("/favicon.ico")    /* 排除掉 /favicon.ico */
////                .setAuth(obj -> {
////                    System.out.println("---------- 进入Sa-Token全局认证 -----------");
////
////                    // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
////                    SaRouter
////                            .match("/**")    // 拦截的 path 列表，可以写多个 */
////                            .notMatch("/user/**", "/user/sendEmail")        // 排除掉的 path 列表，可以写多个
////                            .check(r -> {
////                                StpUtil.checkLogin();
////                            });
////                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
////
////                    // 更多拦截处理方式，请参考“路由拦截式鉴权”章节 */
////                })
//                // 认证函数: 每次请求执行
//
//                // 异常处理函数：每次认证函数发生异常时执行此函数
////                .setError(e -> {
////                    System.out.println("---------- 进入Sa-Token异常处理 -----------");
////                    log.error("Sa-Token异常",e);
////                    SaHolder.getResponse().setHeader("Content-Type", "application/json;charset=UTF-8");
////                    // 使用封装的 JSON 工具类转换数据格式
////                    return JSON.toJSONString( SaResult.error(e.getMessage()) );
////                })
//
//                // 前置函数：在每次认证函数之前执行
////                .setBeforeAuth(r -> {
////                    // ---------- 设置一些安全响应头 ----------
////                    SaHolder.getResponse()
////                            // 服务器名称
////                            .setServer("sa-server")
////                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
////                            .setHeader("X-Frame-Options", "SAMEORIGIN")
////                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
////                            .setHeader("X-XSS-Protection", "1; mode=block")
////                            // 禁用浏览器内容嗅探
////                            .setHeader("X-Content-Type-Options", "nosniff")
////                    ;
////                })
//                ;
////    }
//}
