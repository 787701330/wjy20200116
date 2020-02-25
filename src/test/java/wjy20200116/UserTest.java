package wjy20200116;

import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjy.space.pojo.User;
import com.wjy.space.service.UserService;
import com.wjy.space.util.FastDfsUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserTest {
	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void testSalt() throws Exception {
		SimpleHash s=new SimpleHash("md5","root","root",3);
		System.out.println(s);
	}
	
	@Test
	public void testUser() throws Exception {
		User user=new User();
		user.setUsername("root34");
		user.setPassword("7410adb249055d9b0b2c048379055849");
		user.setRoleId((long) 1);
		user.setState("1");
		user.setSalt("1");
		user.setCreated(new Date());
		user.setUpdated(new Date());
		userService.insert(user);
	}
	
	@Test
	public void testFastDFS() throws Exception {
		int file1 = FastDfsUtil.fdfsDeleteFile1("group1/M00/00/00/wKgBCl45K7-AP-90AACpx6CDwn87..xlsx");
		System.out.println(file1);
	}
}
