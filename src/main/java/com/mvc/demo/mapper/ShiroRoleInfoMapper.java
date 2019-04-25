package com.mvc.demo.mapper;

import com.mvc.demo.pojo.ShiroRoleInfo;
import com.mvc.demo.pojo.ShiroRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShiroRoleInfoMapper {
    int countByExample(ShiroRoleInfoExample example);

    int deleteByExample(ShiroRoleInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShiroRoleInfo record);

    int insertSelective(ShiroRoleInfo record);

    List<ShiroRoleInfo> getRolesByUserId(Long userId);

    List<ShiroRoleInfo> selectByExample(ShiroRoleInfoExample example);

    ShiroRoleInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShiroRoleInfo record, @Param("example") ShiroRoleInfoExample example);

    int updateByExample(@Param("record") ShiroRoleInfo record, @Param("example") ShiroRoleInfoExample example);

    int updateByPrimaryKeySelective(ShiroRoleInfo record);

    int updateByPrimaryKey(ShiroRoleInfo record);
}