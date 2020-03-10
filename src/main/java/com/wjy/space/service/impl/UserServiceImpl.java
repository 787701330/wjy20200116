package com.wjy.space.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjy.space.mapper.UserMapper;
import com.wjy.space.pojo.User;
import com.wjy.space.pojo.UserExample;
import com.wjy.space.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	public int insert(User user) {
		
		return userMapper.insert(user);
	}
	
	
	public List<User> selectByExample(UserExample example) {
		return userMapper.selectByExample(example);
	}
	
	@Override
	public int updatePassword(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

}
