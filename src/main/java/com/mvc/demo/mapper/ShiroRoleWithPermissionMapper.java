package com.mvc.demo.mapper;

import com.mvc.demo.pojo.ShiroRoleWithPermission;
import com.mvc.demo.pojo.ShiroRoleWithPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShiroRoleWithPermissionMapper {
    int countByExample(ShiroRoleWithPermissionExample example);

    int deleteByExample(ShiroRoleWithPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShiroRoleWithPermission record);

    int insertSelective(ShiroRoleWithPermission record);

    List<ShiroRoleWithPermission> selectByExample(ShiroRoleWithPermissionExample example);

    ShiroRoleWithPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShiroRoleWithPermission record, @Param("example") ShiroRoleWithPermissionExample example);

    int updateByExample(@Param("record") ShiroRoleWithPermission record, @Param("example") ShiroRoleWithPermissionExample example);

    int updateByPrimaryKeySelective(ShiroRoleWithPermission record);

    int updateByPrimaryKey(ShiroRoleWithPermission record);
}