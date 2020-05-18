package cn.wujunya.space.service;

import java.util.List;

import cn.wujunya.space.pojo.Permission;
import cn.wujunya.space.pojo.PermissionExample;

public interface PermissionService {

	List<Permission> selectByExample(PermissionExample example);
	
}
