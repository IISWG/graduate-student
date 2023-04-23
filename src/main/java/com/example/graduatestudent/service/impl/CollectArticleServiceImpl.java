package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.CollectArticle;
import com.example.graduatestudent.mapper.CollectArticleMapper;
import com.example.graduatestudent.service.ICollectArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章收藏表 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-04-06
 */
@Service
public class CollectArticleServiceImpl extends ServiceImpl<CollectArticleMapper, CollectArticle> implements ICollectArticleService {

    @Resource
    CollectArticleMapper collectArticleMapper;


}
