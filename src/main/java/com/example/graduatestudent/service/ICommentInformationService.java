package com.example.graduatestudent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduatestudent.entity.CommentInformation;

import java.util.List;

/**
 * <p>
 * 评论信息 服务类
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
public interface ICommentInformationService extends IService<CommentInformation> {
    List<CommentInformation> getCommentByArticleId(Long articleId);

}
