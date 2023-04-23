package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.*;
import com.example.graduatestudent.entity.param.SelectArticleParam;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.mapper.*;
import com.example.graduatestudent.service.IArticleInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 文章信息 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Service
@Slf4j
public class ArticleInformationServiceImpl extends ServiceImpl<ArticleInformationMapper, ArticleInformation> implements IArticleInformationService {

    @Resource
    ArticleInformationMapper articleInformationMapper;
    @Resource
    ArticleTagMapper articleTagMapper;
    @Resource
    TagMapMapper tagMapMapper;
    @Resource
    ArticleAdditionalInformationMapper articleAdditionalInformationMapper;
    @Resource
    CollectArticleMapper collectArticleMapper;
    @Resource
    AttentionMapper attentionMapper;
    @Resource
    UserInformationMapper userInformationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertArticle(ArticleInformation articleInformation) {
        log.info("文章插入前的信息情况，articleInformation：{}", articleInformation);
        Long id = articleInformationMapper.insertArticleInformation(articleInformation);
        log.info("文章插入后的信息情况，articleInformation：{}", articleInformation);
        Long articleId = articleInformation.getId();
        ArticleAdditionalInformation articleAdditionalInformation = new ArticleAdditionalInformation();
        articleAdditionalInformation.setArticleId(articleId);
        int insertArtAddInfo = articleAdditionalInformationMapper.insert(articleAdditionalInformation);
        if (insertArtAddInfo == 0) {
            log.error("添加文章附加信息失败！");
            throw new RuntimeException("添加文章附加信息失败！");
        }
        if (id != 0) {

            List<ArticleTag> articleLabelList = articleInformation.getArticleLabelList();
            if (articleLabelList != null && articleLabelList.size() != 0) {
                for (ArticleTag articleLabel : articleLabelList) {
                    Integer labelId = articleLabel.getId();
                    if (labelId == null) {
                        articleLabel.setNumber(1);
                        articleTagMapper.insert(articleLabel);
                        if ((labelId = articleLabel.getId()) == null) {
                            log.error("添加标签失败！");
                            throw new RuntimeException("添加标签失败！");
                        }
                    } else {
                        articleLabel.setNumber(articleLabel.getNumber() + 1);
                        articleTagMapper.updateById(articleLabel);
                    }
                    int insertTagMap = tagMapMapper.insert(new TagMap().setTagId(labelId).setArticleId(articleId));
                    if (insertTagMap == 0) {
                        log.error("添加文章标签映射失败！");
                        throw new RuntimeException("添加文章标签映射失败！");
                    }
                }
            }
        } else {
            throw new RuntimeException("添加文章失败！");
        }
        return 1;
    }

    @Override
    public PageResult getArticleListByUserId(SelectArticleParam selectArticleParam) {
        QueryWrapper<ArticleInformation> articleInformationQueryWrapper = new QueryWrapper<>();
        if (selectArticleParam.getUserId() != null) {
            articleInformationQueryWrapper.eq("user_id", selectArticleParam.getUserId());
        }
        if (selectArticleParam.getType() != null) {
            articleInformationQueryWrapper.eq("article_type", selectArticleParam.getType());
        }
        PageResult pageResult = new PageResult();
        Page<ArticleInformation> page = new Page<>(selectArticleParam.getPageNum(), selectArticleParam.getPageSize());
        IPage<ArticleInformation> selectPage = articleInformationMapper.selectPage(page, articleInformationQueryWrapper);
        log.info("pages:" + selectPage.getPages());
        log.info("total:" + selectPage.getTotal());
        pageResult.setPages(selectPage.getPages());
        pageResult.setTotal(selectPage.getTotal());
        List<ArticleInformation> records = selectPage.getRecords();
        for (ArticleInformation record : records) {
            List<TagMap> tagMapList = tagMapMapper.selectList(new QueryWrapper<TagMap>().eq("article_id", record.getId()));
            ArrayList<ArticleTag> articleTags = new ArrayList<>();
            for (TagMap tagMap : tagMapList) {
                ArticleTag articleTag = articleTagMapper.selectById(tagMap.getTagId());
                articleTags.add(articleTag);
            }
            ArticleAdditionalInformation articleAdditionalInformation = articleAdditionalInformationMapper
                    .selectOne(new QueryWrapper<ArticleAdditionalInformation>()
                            .eq("article_id", record.getId()));
            UserInformation userInformation = userInformationMapper.selectById(record.getUserId());
//            List<ArticleTag> articleLabelList = articleTagMapper.selectList(new QueryWrapper<ArticleTag>().eq("article_id", record.getId()));
            record.setLabelList(articleTags).setUserInformation(userInformation);
            record.setArticleAdditionalInformation(articleAdditionalInformation);
        }
        pageResult.setRecords(records);
        return pageResult;
    }

    @Override
    public List<ArticleInformation> getCollectArticleListByUserId(String userId) {
        List<CollectArticle> collectArticleList = collectArticleMapper.selectList(new QueryWrapper<CollectArticle>()
                .eq("user_id", userId));
        ArrayList<ArticleInformation> articleInformations = new ArrayList<>();
        for (CollectArticle collectArticle : collectArticleList) {
            ArticleInformation articleInformation = articleInformationMapper.selectById(collectArticle.getArticleId());
            articleInformations.add(articleInformation);
        }
        for (ArticleInformation record : articleInformations) {
            List<TagMap> tagMapList = tagMapMapper.selectList(new QueryWrapper<TagMap>().eq("article_id", record.getId()));
            ArrayList<ArticleTag> articleTags = new ArrayList<>();
            for (TagMap tagMap : tagMapList) {
                ArticleTag articleTag = articleTagMapper.selectById(tagMap.getTagId());
                articleTags.add(articleTag);
            }
            ArticleAdditionalInformation articleAdditionalInformation = articleAdditionalInformationMapper
                    .selectOne(new QueryWrapper<ArticleAdditionalInformation>()
                            .eq("article_id", record.getId()));
            UserInformation userInformation = userInformationMapper.selectById(record.getUserId());
            record.setLabelList(articleTags).setUserInformation(userInformation);
            record.setArticleAdditionalInformation(articleAdditionalInformation);
        }

        return articleInformations;
    }

    @Override
    public List<ArticleInformation> getAttentionUserOfArticle(String userId) {
        List<Attention> attentionList = attentionMapper.selectList(new QueryWrapper<Attention>()
                .eq("user_id", userId).eq("is_attention",1));
        ArrayList<ArticleInformation> articleInformations = new ArrayList<>(attentionList.size() * 10);
        for (Attention attention : attentionList) {
            UserInformation userInformation = userInformationMapper.selectById(attention.getFocusUserId());
            Page<ArticleInformation> articleInformationPage = new Page<>(1, 10);
            Page<ArticleInformation> articleInformationList = articleInformationMapper.selectPage(articleInformationPage, new QueryWrapper<ArticleInformation>()
                    .eq("user_id", attention.getFocusUserId()));
            List<ArticleInformation> records = articleInformationList.getRecords();
            for (ArticleInformation record : records) {
                record.setUserInformation(userInformation);
            }
            articleInformations.addAll(records);
        }
        for (ArticleInformation record : articleInformations) {
            List<TagMap> tagMapList = tagMapMapper.selectList(new QueryWrapper<TagMap>().eq("article_id", record.getId()));
            ArrayList<ArticleTag> articleTags = new ArrayList<>();
            for (TagMap tagMap : tagMapList) {
                ArticleTag articleTag = articleTagMapper.selectById(tagMap.getTagId());
                articleTags.add(articleTag);
            }
            ArticleAdditionalInformation articleAdditionalInformation = articleAdditionalInformationMapper
                    .selectOne(new QueryWrapper<ArticleAdditionalInformation>()
                            .eq("article_id", record.getId()));
            record.setLabelList(articleTags);
            record.setArticleAdditionalInformation(articleAdditionalInformation);
        }
        Collections.sort(articleInformations, new Comparator<ArticleInformation>() {
            @Override
            public int compare(ArticleInformation o1, ArticleInformation o2) {
                return o2.getLatestUpdateTime().toString().compareTo(o1.getLatestUpdateTime().toString());
            }
        });
        return articleInformations;
    }
}
