package cn.wujunya.space.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wujunya.space.mo.MessageObject;
import cn.wujunya.space.pojo.User;
import cn.wujunya.space.pojo.UserExample;
import cn.wujunya.space.pojo.UserExample.Criteria;
import cn.wujunya.space.service.UserService;
import cn.wujunya.space.util.EmptyStringToNullUtil;
import cn.wujunya.space.util.SaltUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/addUser.do")
	public String addUser() {
		return "user_add";
	}

	@RequestMapping("/updatePassword.do")
	public String updatePassword() {
		return "updatePassword";
	}
	
	@RequestMapping("/personalInfomation.do")
	public String personalInfomation() {
		return "personalInfomation";
	}

	@ResponseBody
	@RequestMapping("/updateInfomation.do")
	public MessageObject updateInfomation(User user,HttpServletRequest request) {
		MessageObject mo = null;
		int i=userService.updateByPrimaryKeySelective(user);
		User user2 = userService.selectByPrimaryKey(user.getId());
		request.getSession().setAttribute("user", user2);
		if (i == 0) {
			mo = new MessageObject(0, "修改失败！");
		} else {
			mo = new MessageObject(1, "修改成功！");
		}
		return mo;
	}
	
	@ResponseBody
	@RequestMapping("/updatePassword2.do")
	public MessageObject updatePassword2(Long userId, String password, String newPassword) {
		MessageObject mo = null;
		User user = userService.selectByPrimaryKey(userId);
		String salt = user.getSalt();
		SimpleHash s=new SimpleHash("md5",password,salt,3);
		if (s.toString().equals(user.getPassword())) {
			User user2 = new User();
			user2.setId(userId);
			user2.setPassword(newPassword);
			int result = userService.updatePassword(user2);
			if (result == 0) {
				mo = new MessageObject(0, "修改密码失败！");
			} else {
				mo = new MessageObject(1, "修改密码成功！");
			}
		} else {
			mo = new MessageObject(0, "修改密码失败！");
		}
		return mo;
	}

	@RequestMapping("/testPassword.do")
	@ResponseBody
	public String testPassword(@Param("password") String password, Long userId) {
		User user = userService.selectByPrimaryKey(userId);
		String salt = user.getSalt();
		SimpleHash s=new SimpleHash("md5",password,salt,3);
		if (s.toString().equals(user.getPassword())) {
			return "true";
		} else {
			return "false";
		}
	}

	@RequestMapping("/testUsername.do")
	@ResponseBody
	public String testUsername(@Param("username") String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username.trim());
		List<User> list = userService.selectByExample(example);
		if (list.size() > 0) {
			return "false";
		} else {
			return "true";
		}
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public MessageObject add(User user) {
		System.out.println("addUser");
		user = (User) EmptyStringToNullUtil.EmptyStringToNull(user);
		String password=user.getPassword();
		String salt=SaltUtil.getSalt();
		SimpleHash s=new SimpleHash("md5",password,salt,3);
		user.setSalt(salt);
		user.setPassword(s.toString());
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
