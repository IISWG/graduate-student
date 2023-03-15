package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.ArticleType;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.mapper.ArticleTypeMapper;
import com.example.graduatestudent.service.IArticleTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章类型 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Service
public class ArticleTypeServiceImpl extends ServiceImpl<ArticleTypeMapper, ArticleType> implements IArticleTypeService {

    @Resource
    ArticleInformationServiceImpl articleInformationService;

    @ApiOperation(value = "插入文章", notes = "")
    @PostMapping("/insertArticle")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult insertArticle(@RequestBody ArticleInformation articleInformation) {
        System.out.println("articleInformation:"+articleInformation);
        LocalDateTime now = LocalDateTime.now();
        articleInformation.setCreateTime(now).setLatestUpdateTime(now);
        try {
            Integer insertNumber = articleInformationService.insertArticle(articleInformation);
            return new OkResult("");
        } catch (Exception e) {
            log.error("插入文章失败",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult("插入文章失败");
        }
    }
}
