package com.wjy.space.mapper;

import com.wjy.space.pojo.Permission;
import com.wjy.space.pojo.PermissionExample;
import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByPrimaryKey(Long permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}