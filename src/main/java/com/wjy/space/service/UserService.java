package com.wjy.space.service;

import java.util.List;

import com.wjy.space.pojo.User;
import com.wjy.space.pojo.UserExample;


public interface UserService {
	
	public int insert(User user);
	
	public List<User> selectByExample(UserExample example);
}
