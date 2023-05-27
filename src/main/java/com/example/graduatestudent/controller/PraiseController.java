package com.example.graduatestudent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.Praise;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.IArticleAdditionalInformationService;
import com.example.graduatestudent.service.IPraiseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * praise 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/praise")
@Slf4j
public class PraiseController {
    @Resource
    IPraiseService praiseService;
    @Resource
    IArticleAdditionalInformationService articleAdditionalInformationService;

    @PostMapping("/updatePraiseArticle")
    @ApiOperation(value = "更新点赞文章", notes = "")
    public BaseResult updatePraiseArticle(@RequestBody Praise praise) {

        praise.setCreateTime(LocalDateTime.now()).setIsPraise(true);
        Praise one = praiseService.getOne(new QueryWrapper<Praise>()
                .eq("user_id", praise.getUserId())
                .eq("article_id", praise.getArticleId()));
        try {
            boolean update;
            boolean articleAddUpdate;
            Praise result;
            if (one == null) {
                update = praiseService.save(praise);
                articleAddUpdate = articleAdditionalInformationService.praAdd1(praise.getArticleId());
                result = praiseService.getOne(new QueryWrapper<Praise>()
                        .eq("user_id", praise.getUserId())
                        .eq("article_id", praise.getArticleId()));
            } else {
                Boolean isPraise = one.getIsPraise();
                if (isPraise) {
                    one.setIsPraise(false);
                    articleAddUpdate = articleAdditionalInformationService.praReduce1(praise.getArticleId());
                } else {
                    one.setIsPraise(true);
                    articleAddUpdate = articleAdditionalInformationService.praAdd1(praise.getArticleId());
                }
                update = praiseService.updateById(one);
                result = one;
            }
            if (update && articleAddUpdate) {
                OkResult okResult = new OkResult("更新文章点赞成功！", result);
                return okResult;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ServerErrResult("更新文章点赞失败！");
            }
        } catch (Exception e) {
            log.error("更新文章点赞时报错", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult("更新文章点赞失败！");
        }
    }

    @GetMapping("/getArticlePraiseUserId")
    public BaseResult getArticlePraiseUserId(Long articleId) {
        log.info("getArticlePraiseUserId,articleId:{}",articleId);
        List<Praise> list = praiseService.list(new QueryWrapper<Praise>()
                .eq("article_id", articleId)
                .eq("is_praise", true));
        return new OkResult(list);
    }
}
