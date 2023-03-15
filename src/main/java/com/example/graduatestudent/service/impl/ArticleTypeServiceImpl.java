package com.example.graduatestudent.service.impl;

import com.example.graduatestudent.entity.ArticleType;
import com.example.graduatestudent.mapper.ArticleTypeMapper;
import com.example.graduatestudent.service.IArticleTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
