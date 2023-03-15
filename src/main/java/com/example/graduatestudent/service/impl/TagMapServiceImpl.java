package com.example.graduatestudent.service.impl;

import com.example.graduatestudent.entity.TagMap;
import com.example.graduatestudent.mapper.TagMapMapper;
import com.example.graduatestudent.service.ITagMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章和标签的关联表 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-14
 */
@Service
public class TagMapServiceImpl extends ServiceImpl<TagMapMapper, TagMap> implements ITagMapService {

}
