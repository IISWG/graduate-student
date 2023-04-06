package com.example.graduatestudent.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.Attention;
import com.example.graduatestudent.entity.result.BaseResult;
import com.example.graduatestudent.entity.result.OkResult;
import com.example.graduatestudent.entity.result.ServerErrResult;
import com.example.graduatestudent.service.IAttentionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 关注信息表 前端控制器
 * </p>
 *
 * @author muxin
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/attention")
@Slf4j
public class AttentionController {
    @Resource
    IAttentionService attentionService;

    @PostMapping("/attention")
    @ApiOperation(value = "关注", notes = "")
    public BaseResult attention(@RequestBody Attention attention) {
        attention.setCreateTime(LocalDateTime.now());
        log.info("attention:{}",attention);
        Attention attentionOne = attentionService.getOne(new QueryWrapper<Attention>()
                .eq("user_id", attention.getUserId())
                .eq("focus_user_id", attention.getFocusUserId()));
        try {
            boolean save;
            if(attentionOne == null){
                save = attentionService.save(attention);
            }else {
                attentionOne.setIsAttention("1");
                save = attentionService.updateById(attentionOne);
            }
            if (save) {
                Attention attentionServiceById = attentionService.getById(attention.getId());
                OkResult okResult = new OkResult("关注成功！", attentionServiceById);
                return okResult;
            } else {
                return new ServerErrResult("关注失败！");
            }
        } catch (Exception e) {

            log.error("关注报错", e);
            return new ServerErrResult("关注失败！");
        }
    }

    @PostMapping("/deleteAttention")
    @ApiOperation(value = "取消关注", notes = "")
    public BaseResult deleteAttention(@RequestBody Attention attention) {
        attention.setCreateTime(LocalDateTime.now()).setIsAttention("0");
        log.info("attention:{}",attention);
        try {
            boolean save = attentionService.saveOrUpdate(attention);
            if (save) {
                OkResult okResult = new OkResult("取消关注成功！", "");
                return okResult;
            } else {
                return new ServerErrResult("取消关注失败！");
            }
        } catch (Exception e) {

            log.error("取消关注报错", e);
            return new ServerErrResult("取消关注失败！");
        }
    }

    @GetMapping("getAttentionListByUserId")
    public BaseResult getAttentionListByUserId(Long userId) {
        try {
            List<Attention> attentionList = attentionService.list(new QueryWrapper<Attention>()
                    .eq("user_id", userId).eq("is_attention","1"));
            OkResult okResult = new OkResult(attentionList);
            return okResult;
        } catch (Exception e) {
            log.error("获取关注列表报错", e);
            return new ServerErrResult("获取关注列表！");
        }
    }


}
