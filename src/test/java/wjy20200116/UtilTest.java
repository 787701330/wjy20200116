package wjy20200116;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wujunya.space.controller.UserController;
import cn.wujunya.space.pojo.User;
import cn.wujunya.space.service.StorageService;
import cn.wujunya.space.util.EmptyStringToNullUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UtilTest {

	
	@Autowired
	private StorageService storageService;
	
	@Test
	public void testUtil() throws Exception {
		User user=new User();
		user.setPhone("");
		User object = (User) EmptyStringToNullUtil.EmptyStringToNull(user);
		System.out.println(user.getPhone());
	}
	
	@Test
	public void testRondomNumber() throws Exception {
		String string = UUID.randomUUID().toString();
		System.out.println(string);
	}
	
	@Test
	public void testDelete() throws Exception {
		storageService.delete(new Long[] {(long) 5});
	}
}
