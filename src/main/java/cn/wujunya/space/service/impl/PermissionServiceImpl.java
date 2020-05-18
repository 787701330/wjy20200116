package cn.wujunya.space.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.wujunya.space.mapper.PermissionMapper;
import cn.wujunya.space.pojo.Permission;
import cn.wujunya.space.pojo.PermissionExample;
import cn.wujunya.space.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	private PermissionMapper permissionMapper;
	

	@Override
	public List<Permission> selectByExample(PermissionExample example) {

		return permissionMapper.selectByExample(example);
	}


}
