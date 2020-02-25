package com.wjy.space.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wjy.space.mapper.PermissionMapper;
import com.wjy.space.pojo.Permission;
import com.wjy.space.pojo.PermissionExample;
import com.wjy.space.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	private PermissionMapper permissionMapper;
	

	@Override
	public List<Permission> selectByExample(PermissionExample example) {

		return permissionMapper.selectByExample(example);
	}


}
