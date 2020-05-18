package cn.wujunya.space.service.impl;

import org.springframework.stereotype.Service;

import cn.wujunya.space.mapper.RoleMapper;
import cn.wujunya.space.pojo.Role;
import cn.wujunya.space.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService{

	
	private RoleMapper roleMapper;

	public Role selectByPrimaryKey(Long roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}
	

}
