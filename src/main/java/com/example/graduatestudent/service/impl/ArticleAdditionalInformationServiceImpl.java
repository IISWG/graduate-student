package com.example.graduatestudent.service.impl;

import com.example.graduatestudent.entity.ArticleAdditionalInformation;
import com.example.graduatestudent.mapper.ArticleAdditionalInformationMapper;
import com.example.graduatestudent.service.IArticleAdditionalInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章的附加信息表 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Service
public class ArticleAdditionalInformationServiceImpl extends ServiceImpl<ArticleAdditionalInformationMapper, ArticleAdditionalInformation> implements IArticleAdditionalInformationService {

    @Resource
    ArticleAdditionalInformationMapper articleAdditionalInformationMapper;

    @Override
    public boolean colAdd1(Long articleId) {
        return articleAdditionalInformationMapper.colAdd1(articleId);
    }
    @Override
    public boolean colReduce1(Long articleId) {
        return articleAdditionalInformationMapper.colReduce1(articleId);
    }

    @Override
    public boolean praAdd1(Long articleId) {
        return articleAdditionalInformationMapper.praAdd1(articleId);
    }

    @Override
    public boolean praReduce1(Long articleId) {
        return articleAdditionalInformationMapper.praReduce1(articleId);
    }

}
