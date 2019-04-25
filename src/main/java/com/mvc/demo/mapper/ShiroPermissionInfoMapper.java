package com.mvc.demo.mapper;

import com.mvc.demo.pojo.ShiroPermissionInfo;
import com.mvc.demo.pojo.ShiroPermissionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShiroPermissionInfoMapper {
    int countByExample(ShiroPermissionInfoExample example);

    int deleteByExample(ShiroPermissionInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShiroPermissionInfo record);

    int insertSelective(ShiroPermissionInfo record);

    List<ShiroPermissionInfo> getPermissonListByUserId(Long userId);

    List<ShiroPermissionInfo> selectByExample(ShiroPermissionInfoExample example);

    ShiroPermissionInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShiroPermissionInfo record, @Param("example") ShiroPermissionInfoExample example);

    int updateByExample(@Param("record") ShiroPermissionInfo record, @Param("example") ShiroPermissionInfoExample example);

    int updateByPrimaryKeySelective(ShiroPermissionInfo record);

    int updateByPrimaryKey(ShiroPermissionInfo record);
}