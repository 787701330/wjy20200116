package com.wjy.space.service;

import java.util.List;

import com.wjy.space.pojo.Permission;
import com.wjy.space.pojo.PermissionExample;

public interface PermissionService {

	List<Permission> selectByExample(PermissionExample example);
	
}
