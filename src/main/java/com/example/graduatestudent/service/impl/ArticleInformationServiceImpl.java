package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.ArticleAdditionalInformation;
import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.ArticleTag;
import com.example.graduatestudent.entity.TagMap;
import com.example.graduatestudent.entity.param.SelectArticleParam;
import com.example.graduatestudent.entity.result.PageResult;
import com.example.graduatestudent.mapper.ArticleAdditionalInformationMapper;
import com.example.graduatestudent.mapper.ArticleInformationMapper;
import com.example.graduatestudent.mapper.ArticleTagMapper;
import com.example.graduatestudent.mapper.TagMapMapper;
import com.example.graduatestudent.service.IArticleInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
//            List<ArticleTag> articleLabelList = articleTagMapper.selectList(new QueryWrapper<ArticleTag>().eq("article_id", record.getId()));
            record.setLabelList(articleTags);
            record.setArticleAdditionalInformation(articleAdditionalInformation);
        }
        pageResult.setRecords(records);
        return pageResult;
    }
}
