package com.wjy.space.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.wjy.space.pojo.Permission;
import com.wjy.space.pojo.PermissionExample;
import com.wjy.space.pojo.Role;
import com.wjy.space.pojo.User;
import com.wjy.space.pojo.UserExample;
import com.wjy.space.pojo.UserExample.Criteria;
import com.wjy.space.service.PermissionService;
import com.wjy.space.service.RoleService;
import com.wjy.space.service.UserService;

public class CustomRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		Role role=roleService.selectByPrimaryKey(user.getRoleId());
		String permissionIds=role.getPermissionIds();
		String[] permissionIdsArr=permissionIds.split(",");
		List<Long> permissionIdsList=new ArrayList<Long>();
		for(String permissionId:permissionIdsArr) {
			permissionIdsList.add(Long.valueOf(permissionId));
		}
		PermissionExample example=new PermissionExample();
		com.wjy.space.pojo.PermissionExample.Criteria criteria = example.createCriteria();
		criteria.andPermissionIdIn(permissionIdsList);
		List<Permission> permissions=permissionService.selectByExample(example);
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		for(Permission permission:permissions) {
			String expression=permission.getExpression();
			if(StringUtils.isNotBlank(expression)) {
				authorizationInfo.addStringPermission(expression);
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username=(String)token.getPrincipal();
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userService.selectByExample(example);
		User user=list.size()==1 ? list.get(0):null;
		if(user!=null) {
			Object password = user.getPassword();
			ByteSource salt = ByteSource.Util.bytes(user.getSalt());
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,password,salt,this.getName());
			return simpleAuthenticationInfo;
		}
		return null;
	}

}
