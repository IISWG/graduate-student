package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.ArticleType;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.service.IArticleTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 文章类型 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/articleType")
public class ArticleTypeController {
    @Resource
    IArticleTypeService articleTypeService;
    @GetMapping("/getAllArticleType")
    @ApiOperation(value = "获取文章类型", notes = "")
    public BaseResult getAllArticleType() {
        List<ArticleType> articleTypeList = articleTypeService.list();
        return new OkResult(articleTypeList);
    }
}
