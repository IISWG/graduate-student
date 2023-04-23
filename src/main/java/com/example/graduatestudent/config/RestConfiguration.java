package com.example.graduatestudent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author xxx
 * @create 2018/06/28 15:32
 * @description RestTemplate配置类:
 * 1.将 HttpClient 作为 RestTemplate 的实现,添加 httpclient 依赖即可
 * 2.设置响应类型和内容类型
 */
@Configuration
public class RestConfiguration {
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        return builder
                .additionalMessageConverters(new WxMappingJackson2HttpMessageConverter())
                .build();
    }

    class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        WxMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = Arrays.asList(
                    MediaType.TEXT_PLAIN,
                    MediaType.TEXT_HTML,
                    MediaType.APPLICATION_JSON_UTF8
            );
            setSupportedMediaTypes(mediaTypes);// tag6
        }
    }

}