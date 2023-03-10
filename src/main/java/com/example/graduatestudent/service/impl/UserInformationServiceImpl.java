package com.example.graduatestudent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graduatestudent.entity.UserInformation;
import com.example.graduatestudent.mapper.UserInformationMapper;
import com.example.graduatestudent.service.IUserInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author muxin
 * @since 2023-03-10
 */
@Service
public class UserInformationServiceImpl extends ServiceImpl<UserInformationMapper, UserInformation> implements IUserInformationService {
    @Resource
    UserInformationMapper userInformationMapper;
    public UserInformation selectOneByEmail(String email) {
        UserInformation userInformation = userInformationMapper.selectOne(new QueryWrapper<UserInformation>().eq("email", email));
        return userInformation;
    }
}
