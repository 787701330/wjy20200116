package com.wjy.space.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjy.space.mo.MessageObject;
import com.wjy.space.pojo.User;
import com.wjy.space.pojo.UserExample;
import com.wjy.space.pojo.UserExample.Criteria;
import com.wjy.space.service.UserService;
import com.wjy.space.util.EmptyStringToNullUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/addUser.do")
	public String addUser() {
		System.out.println(25);
		return "user_add";
	}
	
	@RequestMapping("/testUsername.do")
	@ResponseBody
	public String testUsername(@Param("username")String username) {
		UserExample example=new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username.trim());
		List<User> list = userService.selectByExample(example);
		if(list.size()>0) {
			return "false";
		}else {
			return "true";
		}
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public MessageObject add(User user) {
		user= (User) EmptyStringToNullUtil.EmptyStringToNull(user);
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		int delete = userService.insert(user);
		MessageObject mo = null;
		if (delete == 0) {
			mo = new MessageObject(0, "新建用户失败！");
		} else {
			mo = new MessageObject(1, "新建用户成功！");
		}
		return mo;
	}
}
