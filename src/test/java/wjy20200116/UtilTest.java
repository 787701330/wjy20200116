package wjy20200116;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjy.space.pojo.User;
import com.wjy.space.util.EmptyStringToNullUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UtilTest {

	@Test
	public void testUtil() throws Exception {
		User user=new User();
		user.setPhone("");
		User object = (User) EmptyStringToNullUtil.EmptyStringToNull(user);
		System.out.println(user.getPhone());
	}
}
