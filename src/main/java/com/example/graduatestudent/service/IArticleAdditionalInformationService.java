package com.example.graduatestudent.service;

import com.example.graduatestudent.entity.ArticleAdditionalInformation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章的附加信息表 服务类
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
public interface IArticleAdditionalInformationService extends IService<ArticleAdditionalInformation> {
    public boolean colAdd1(Long articleId);

    boolean colReduce1(Long articleId);
}
