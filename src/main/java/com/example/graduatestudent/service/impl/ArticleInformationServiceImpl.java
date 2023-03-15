package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.ArticleInformation;
import com.example.graduatestudent.entity.ArticleTag;
import com.example.graduatestudent.entity.TagMap;
import com.example.graduatestudent.mapper.ArticleInformationMapper;
import com.example.graduatestudent.mapper.ArticleTagMapper;
import com.example.graduatestudent.mapper.TagMapMapper;
import com.example.graduatestudent.service.IArticleInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
public class ArticleInformationServiceImpl extends ServiceImpl<ArticleInformationMapper, ArticleInformation> implements IArticleInformationService {

    @Resource
    ArticleInformationMapper articleInformationMapper;
    @Resource
    ArticleTagMapper articleTagMapper;
    @Resource
    TagMapMapper tagMapMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertArticle(ArticleInformation articleInformation) {
        System.out.println("articleInformation:"+articleInformation);
        Long id = articleInformationMapper.insertArticleInformation(articleInformation);
        System.out.println("insertArticleInformation:"+articleInformation);
        Long articleId = articleInformation.getId();
        if (id != 0) {
            List<ArticleTag> articleLabelList = articleInformation.getArticleLabelList();
            if (articleLabelList != null && articleLabelList.size() != 0) {
                for (ArticleTag articleLabel : articleLabelList) {
                    Integer labelId = articleLabel.getId();
                    if (labelId == null) {
                        articleTagMapper.insert(articleLabel);
                        if ((labelId = articleLabel.getId()) == null) {
                            log.error("添加标签失败！");
                            throw new RuntimeException("添加标签失败！");
                        }
                    }
                    int insertTagMap = tagMapMapper.insert(new TagMap().setTagId(labelId).setArticleId(articleId));
                    if (insertTagMap == 0) {
                        log.error("添加文章标签映射失败！");
                        throw new RuntimeException("添加文章标签映射失败！");
                    }

//                    if (insert == 0) {
//                        log.error("添加标签失败！");
//                        throw new RuntimeException("添加标签失败！");
//                    }
                }
            }

        } else {
            throw new RuntimeException("添加文章失败！");
        }
        return 1;
    }
}
