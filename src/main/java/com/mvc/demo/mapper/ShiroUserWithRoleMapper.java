package com.mvc.demo.mapper;

import com.mvc.demo.pojo.ShiroUserWithRole;
import com.mvc.demo.pojo.ShiroUserWithRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShiroUserWithRoleMapper {
    int countByExample(ShiroUserWithRoleExample example);

    int deleteByExample(ShiroUserWithRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShiroUserWithRole record);

    int insertSelective(ShiroUserWithRole record);

    List<ShiroUserWithRole> selectByExample(ShiroUserWithRoleExample example);

    ShiroUserWithRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShiroUserWithRole record, @Param("example") ShiroUserWithRoleExample example);

    int updateByExample(@Param("record") ShiroUserWithRole record, @Param("example") ShiroUserWithRoleExample example);

    int updateByPrimaryKeySelective(ShiroUserWithRole record);

    int updateByPrimaryKey(ShiroUserWithRole record);
}