package com.example.graduatestudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.param.SelectArticlCirleParam;
import com.example.graduatestudent.entity.param.SelectArticleParam;
import com.example.graduatestudent.entity.result.PageResult;
import com.meilisearch.sdk.exceptions.MeilisearchException;

import java.util.List;

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

    List<ArticleInformation> getCollectArticleListByUserId(String userId);

    List<ArticleInformation> getAttentionUserOfArticle(String userId);

//    List<ArticleInformation> getCircleOfArticle(String majorCode, Long schoolId) throws MeilisearchException;

  //  List<ArticleInformation> getCircleOfArticle(String content, String majorCode, String schoolId) throws MeilisearchException;

    PageResult getCircleOfArticle(SelectArticlCirleParam selectArticlCirleParam) throws MeilisearchException;

    //List<ArticleInformation> getCircleOfArticle(SelectArticlCirleParam selectArticlCirleParam);
}
