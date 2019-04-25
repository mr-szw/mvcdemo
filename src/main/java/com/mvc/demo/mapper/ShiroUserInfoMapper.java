package com.mvc.demo.mapper;

import com.mvc.demo.pojo.ShiroUserInfo;
import com.mvc.demo.pojo.ShiroUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShiroUserInfoMapper {
    int countByExample(ShiroUserInfoExample example);

    int deleteByExample(ShiroUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    ShiroUserInfo getUserInfoByLoginName(String loginName);

    int insert(ShiroUserInfo record);

    int insertSelective(ShiroUserInfo record);

    List<ShiroUserInfo> selectByExample(ShiroUserInfoExample example);

    ShiroUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShiroUserInfo record, @Param("example") ShiroUserInfoExample example);

    int updateByExample(@Param("record") ShiroUserInfo record, @Param("example") ShiroUserInfoExample example);

    int updateByPrimaryKeySelective(ShiroUserInfo record);

    int updateByPrimaryKey(ShiroUserInfo record);
}