package com.example.graduatestudent.service;

import com.example.graduatestudent.entity.UserInformation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author muxin
 * @since 2023-03-10
 */
public interface IUserInformationService extends IService<UserInformation> {
    public UserInformation selectOneByEmail(String email);
}
