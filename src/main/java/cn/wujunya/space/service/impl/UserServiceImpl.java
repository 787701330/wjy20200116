package cn.wujunya.space.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wujunya.space.mapper.UserMapper;
import cn.wujunya.space.pojo.User;
import cn.wujunya.space.pojo.UserExample;
import cn.wujunya.space.service.UserService;

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


	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}


	@Override
	public int updateByPrimaryKeySelective(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

}
