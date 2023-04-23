package com.example.graduatestudent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.CollectArticle;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.IArticleAdditionalInformationService;
import com.example.graduatestudent.service.ICollectArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 文章收藏表 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/collectArticle")
@Slf4j
public class CollectArticleController {
    @Resource
    ICollectArticleService collectArticleService;
    @Resource
    IArticleAdditionalInformationService articleAdditionalInformationService;

    @PostMapping("/updateCollectArticle")
    @ApiOperation(value = "更新关注文章", notes = "")
    public BaseResult updateCollectArticle(@RequestBody CollectArticle collectArticle) {

        collectArticle.setCreateTime(LocalDateTime.now()).setIsCollect(true);
        CollectArticle one = collectArticleService.getOne(new QueryWrapper<CollectArticle>()
                .eq("user_id", collectArticle.getUserId())
                .eq("article_id", collectArticle.getArticleId()));
        try {
            boolean update;
            boolean articleAddUpdate;
            CollectArticle result;
            if (one == null) {
                update = collectArticleService.save(collectArticle);
                articleAddUpdate = articleAdditionalInformationService.colAdd1(collectArticle.getArticleId());
                result = collectArticleService.getOne(new QueryWrapper<CollectArticle>()
                        .eq("user_id", collectArticle.getUserId())
                        .eq("article_id", collectArticle.getArticleId()));
            } else {
                Boolean isCollect = one.getIsCollect();
                if (isCollect) {
                    one.setIsCollect(false);
                    articleAddUpdate = articleAdditionalInformationService.colReduce1(collectArticle.getArticleId());
                } else {
                    one.setIsCollect(true);
                    articleAddUpdate = articleAdditionalInformationService.colAdd1(collectArticle.getArticleId());
                }
                update = collectArticleService.updateById(one);
                result = one;
            }
            if (update && articleAddUpdate) {
                OkResult okResult = new OkResult("更新文章收藏成功！", result);
                return okResult;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ServerErrResult("更新文章收藏失败！");
            }
        } catch (Exception e) {
            log.error("更新文章收藏时报错", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult("更新文章收藏失败！");
        }
    }

    @GetMapping("/getCollectArticleList")
    public BaseResult getCollectArticleList(Long userId) {
        log.info("getCollectArticleList,userId:{}",userId);
        List<CollectArticle> collectArticleList = collectArticleService.list(new QueryWrapper<CollectArticle>()
                .eq("user_id", userId)
                .eq("is_collect", true));
        return new OkResult(collectArticleList);
    }
}
