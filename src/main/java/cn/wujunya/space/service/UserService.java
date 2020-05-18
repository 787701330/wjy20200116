package cn.wujunya.space.service;

import java.util.List;


import cn.wujunya.space.pojo.User;
import cn.wujunya.space.pojo.UserExample;


public interface UserService {
	
	public int insert(User user);
	
	public List<User> selectByExample(UserExample example);

	int updatePassword(User user);
	
	User selectByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(User user);
}
