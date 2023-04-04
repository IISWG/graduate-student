package com.example.graduatestudent.controller;


import com.example.graduatestudent.entity.CommentInformation;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.impl.CommentInformationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 评论信息 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/commentInformation")
public class CommentInformationController {
    @Resource
    CommentInformationServiceImpl commentInformationService;

    @GetMapping("/getCommentByUserId")
    @ApiOperation(value = "通过文章id获取文章的相关评论信息", notes = "")
    public BaseResult getCommentByArticleId(Long articleId) {
        List<CommentInformation> commentByArticleId = commentInformationService.getCommentByArticleId(articleId);
        return new OkResult(commentByArticleId);
//                return new OkResult(articleListByUserId);
    }

    @PostMapping("/insertComment")
    @ApiOperation(value = "插入文章的评论信息", notes = "")
    @Transactional(rollbackFor = Exception.class)
    public BaseResult insertComment(@RequestBody CommentInformation commentInformation) {
        commentInformation.setCommentTime(LocalDateTime.now());
        try {
            boolean save = commentInformationService.save(commentInformation);
            if (save) {
                return new OkResult("评论成功！", "");
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new ServerErrResult("评论失败!");
//                throw new Exception("评论失败！");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServerErrResult("评论失败!");
        }
    }
}
