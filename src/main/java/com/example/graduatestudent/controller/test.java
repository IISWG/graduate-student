package com.example.graduatestudent.controller;

import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.service.IArticleInformationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wly
 * @date 2022-05-17 15:45:12
 */
@RestController
@Slf4j
public class test {
    @Autowired
    public com.example.graduatestudent.util.httpRequestUtil httpRequestUtil;
    @Autowired
    private ObjectMapper objectMapper;
    private String applicationJson = "application/json";
    private String applicationStr = "application/X-WWW-form-urlencoded";

    @Resource
    IArticleInformationService articleInformationService;
    @GetMapping("/test")
    public String test() {
        StringBuilder userUrl = new StringBuilder("https://api.qz100.com/api-cpp/major/schools?school_name=&page=1&limit=10&sort_type=1&school_type=-1&is_edu=-1&is_local=-1&is_center=-1");
//                .append("?usrtId=").append("111111")
//                .append("&name=").append("zhangsan");
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put( "notesId","1001");
        rootNode.put( "fex", "女");
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", applicationJson);
            HttpEntity httpEntity = new HttpEntity(rootNode.toString(),headers);
            String result = httpRequestUtil.getRequest(userUrl.toString(), HttpMethod.GET,httpEntity,String.class);
            log.info(result);
            return result;
        }catch (Exception e){
            log.error("调用接口失败:"+e.getMessage());
            return null;
        }
    }

    @GetMapping("updateImgUrl")
    public BaseResult updateImgUrl(){
        List<ArticleInformation> list = articleInformationService.list();
        for (ArticleInformation articleInformation : list) {
            String articleCover = articleInformation.getArticleCover();
            String articleContent = articleInformation.getArticleContent();
            articleInformation
                .setArticleCover(articleCover.replaceAll("rrwfyolhs\\.hn-bkt\\.clouddn\\.com", "img.galaxyspace.tech"))
                .setArticleContent(articleContent.replaceAll("rrwfyolhs\\.hn-bkt\\.clouddn\\.com", "img.galaxyspace.tech"));
            articleInformationService.updateById(articleInformation);
        }
        return new OkResult();
    }
}