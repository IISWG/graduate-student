package com.example.graduatestudent.mapper;

import com.example.graduatestudent.entity.UserInformation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author muxin
 * @since 2023-03-10
 */
public interface UserInformationMapper extends BaseMapper<UserInformation> {
    public UserInformation selectOneByEmail(@Param("email") String email);
}
