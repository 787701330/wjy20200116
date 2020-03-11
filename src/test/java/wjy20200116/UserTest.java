package wjy20200116;

import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjy.space.controller.UserController;
import com.wjy.space.pojo.User;
import com.wjy.space.service.UserService;
import com.wjy.space.util.FastDfsUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserController userController;
	
	@Test
	public void testSalt() throws Exception {
		SimpleHash s=new SimpleHash("md5","root","root",3);
		System.out.println(s);
	}
	
	@Test
	public void testUser() throws Exception {
		User user=new User();
		user.setUsername("1111112");
		user.setPassword("111111");
		user.setPhone("");
		user.setEmail("");
		user.setRealname("");
		userController.add(user);
	}
	
	@Test
	public void testFastDFS() throws Exception {
		int file1 = FastDfsUtil.fdfsDeleteFile1("group1/M00/00/00/wKgBCl45K7-AP-90AACpx6CDwn87..xlsx");
		System.out.println(file1);
	}
}
