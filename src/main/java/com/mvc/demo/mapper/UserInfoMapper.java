package com.mvc.demo.mapper;

import com.mvc.demo.entity.UserInfo;

import java.util.Set;

/**
 * @author Dawei 2018/12/1
 */
public interface UserInfoMapper {


    /* 通过用户名获取用户密码 */
    String getUserPassWordByUserName(String userName);

    /* 通过用户名获取角色信息 */
    Set<String> getUserRolesByUserName(String userName);

    /*  通过 用户的登陆信息 获取用户基础信息 */
    UserInfo getUserInfoByLoginName(String loginName);
}
