package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.ArticleTag;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.service.IArticleTagService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章标签表 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/articleTag")


public class ArticleTagController {
    @Resource
    IArticleTagService articleTagService;
    @GetMapping("/getTag")
    @ApiOperation(value = "获取文章标签", notes = "")
    public BaseResult getTag() {
        List<ArticleTag> articleTags = articleTagService.list();
        OkResult okResult = new OkResult("",articleTags);
        return okResult;
    }
}
