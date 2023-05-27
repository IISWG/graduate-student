package com.example.graduatestudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.param.SelectArticlCirleParam;
import com.example.graduatestudent.entity.param.SelectArticleParam;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.PageResult;
import com.meilisearch.sdk.exceptions.MeilisearchException;
import com.meilisearch.sdk.model.TaskInfo;

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

    PageResult getCircleOfArticle(SelectArticlCirleParam selectArticlCirleParam) throws MeilisearchException;

    public ArticleInformation getArticleListByArticleId(Long id);

    BaseResult searchArticle(SelectArticlCirleParam param);

    public TaskInfo addDocuments(Long userId);
}
