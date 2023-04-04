package com.example.graduatestudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.param.SelectArticleParam;
import com.example.graduatestudent.entity.result.PageResult;

/**
 * <p>
 * 文章信息 服务类
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
public interface IArticleInformationService extends IService<ArticleInformation> {

    Integer insertArticle(ArticleInformation articleInformation);

    PageResult getArticleListByUserId(SelectArticleParam selectArticleParam);
}
