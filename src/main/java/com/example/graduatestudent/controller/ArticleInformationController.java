package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.param.SelectArticlCirleParam;
import com.example.graduatestudent.entity.param.SelectArticleParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.IArticleInformationService;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章信息 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/articleInformation")
@Slf4j
public class ArticleInformationController {
    @Resource
    IArticleInformationService articleInformationService;
    /**
     * @description: 插入文章
     * @author: muxin
     * @date: 2023/3/22 10:07
     * @param:
     * @return:
     **/
    @PostMapping("/createArticle")
    @ApiOperation(value = "插入文章", notes = "")
    public BaseResult createArticle(@RequestBody ArticleInformation articleInformation) {
        LocalDateTime now = LocalDateTime.now();
        articleInformation.setCreateTime(now);
        articleInformation.setLatestUpdateTime(now);
        try {
            Integer insertArticle = articleInformationService.insertArticle(articleInformation);
            if (insertArticle != 0) {
                OkResult okResult = new OkResult("创建文章成功！", "");
                return okResult;
            } else {
                return new ServerErrResult("插入文章失败！");
            }
        } catch (Exception e) {

            log.error("插入文章时报错",e);
            return new ServerErrResult("插入文章失败！");
        }


    }

    @GetMapping("/deleteArticle")
    @ApiOperation(value = "插入文章", notes = "")
    public BaseResult deleteArticle(Long articleId) {
        try {
            boolean removeById = articleInformationService.removeById(articleId);
            if (removeById) {
                OkResult okResult = new OkResult("删除文章成功！", "");
                return okResult;
            } else {
                return new ServerErrResult("删除文章失败！");
            }
        } catch (Exception e) {
            log.error("删除文章时报错", e);
            return new ServerErrResult("删除文章失败！");
        }


    }

    @PostMapping("/updateArticle")
    @ApiOperation(value = "更新文章", notes = "")
    public BaseResult updateArticle(@RequestBody ArticleInformation articleInformation) {
        LocalDateTime now = LocalDateTime.now();
        articleInformation.setLatestUpdateTime(now);
        try {
            boolean insertArticle = articleInformationService.updateById(articleInformation);
            if (insertArticle) {
                OkResult okResult = new OkResult("更新文章成功！", "");
                return okResult;
            } else {
                return new ServerErrResult("更新文章失败！");
            }
        } catch (Exception e) {
            log.error("更新文章时报错", e);
            return new ServerErrResult("更新文章失败！");
        }


    }
    @GetMapping("/getArticleListByUserId")
    @ApiOperation(value = "通过用户id获取文章", notes = "")
    public BaseResult getArticleListByUserId(Long userId,Integer pageSize,Integer pageNum) {
        log.info("userId:"+userId+"  pageSize:"+pageSize+"   pageNum:"+pageNum);
        SelectArticleParam selectArticleParam = new SelectArticleParam(userId,null);
        selectArticleParam.setPageSize(pageSize).setPageNum(pageNum);
        PageResult articleListByUserId = articleInformationService.getArticleListByUserId(selectArticleParam);
        return new OkResult(articleListByUserId);
    }

    @GetMapping("/getArticleListByType")
    @ApiOperation(value = "通过文章类型获取文章", notes = "")
    public BaseResult getArticleListByType(String type, Integer pageSize, Integer pageNum) {
        SelectArticleParam selectArticleParam = new SelectArticleParam();
        selectArticleParam.setType(type).setPageSize(pageSize).setPageNum(pageNum);
        PageResult articleListByUserId = articleInformationService.getArticleListByUserId(selectArticleParam);
        return new OkResult(articleListByUserId);
    }

    @GetMapping("/getCollectArticleListByUserId")
    @ApiOperation(value = "通过用户id获取收藏文章", notes = "")
    public BaseResult getCollectArticleListByUserId(String userId) {
        List<ArticleInformation> articleInformations = articleInformationService.getCollectArticleListByUserId(userId);
        return new OkResult(articleInformations);
    }

    @GetMapping("/getAttentionUserOfArticle")
    @ApiOperation(value = "通过收藏用户推荐文章", notes = "")
    public BaseResult getAttentionUserOfArticle(String userId) {
        List<ArticleInformation> articleInformations = articleInformationService.getAttentionUserOfArticle(userId);
        return new OkResult(articleInformations);
    }

    @PostMapping("/getCircleOfArticle")
    @ApiOperation(value = "通过目标专业和目标院校分圈返回文章，按时间排序", notes = "")
    public BaseResult getCircleOfArticle(@RequestBody SelectArticlCirleParam selectArticlCirleParam) throws MeilisearchException {
        log.info("selectArticlCirleParam:{}",selectArticlCirleParam);
        PageResult articleInformations = articleInformationService.getCircleOfArticle(selectArticlCirleParam);
        return new OkResult(articleInformations);
    }
}
