package com.wjy.space.service.impl;

import org.springframework.stereotype.Service;

import com.wjy.space.mapper.RoleMapper;
import com.wjy.space.pojo.Role;
import com.wjy.space.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{

	
	private RoleMapper roleMapper;

	public Role selectByPrimaryKey(Long roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}
	

}
