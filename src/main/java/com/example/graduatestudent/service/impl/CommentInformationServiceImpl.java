package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduatestudent.entity.CommentInformation;
import com.example.graduatestudent.entity.UserInformation;
import com.example.graduatestudent.mapper.CommentInformationMapper;
import com.example.graduatestudent.mapper.UserInformationMapper;
import com.example.graduatestudent.service.ICommentInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 评论信息 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@Service
public class CommentInformationServiceImpl extends ServiceImpl<CommentInformationMapper, CommentInformation> implements ICommentInformationService {

    @Resource
    CommentInformationMapper commentInformationMapper;
    @Resource
    UserInformationMapper userInformationMapper;

    @Override
    public List<CommentInformation> getCommentByArticleId(Long articleId) {
        List<CommentInformation> commentInformations = commentInformationMapper
                .selectList(new QueryWrapper<CommentInformation>()
                        .eq("article_id", articleId)
                        .eq("comment_level", 1));
        for (CommentInformation commentInformation : commentInformations) {
            UserInformation userInformation = userInformationMapper.selectById(commentInformation.getCommentUserId());
            commentInformation.setAvatar(userInformation.getHeadPicture())
                    .setNickname(userInformation.getNickname());
            List<CommentInformation> commentInformationList = commentInformationMapper
                    .selectList(new QueryWrapper<CommentInformation>()
                            .eq("comment_id", commentInformation.getId())
                            .eq("comment_level", 2));
            for (CommentInformation information : commentInformationList) {
                UserInformation commentUser = userInformationMapper.selectById(information.getCommentUserId());
                UserInformation toUser = userInformationMapper.selectById(information.getToId());
                information.setNickname(commentUser.getNickname())
                        .setAvatar(commentUser.getHeadPicture())
                        .setToPerName(toUser.getNickname());
            }
            commentInformation.setCommentInformations(commentInformationList);
        }
        return commentInformations;
    }
}
