package wjy20200116;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wujunya.space.service.StorageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SpaceTest {

	@Autowired
	private StorageService storageService;


	@Test
	public void testAddFolder() throws Exception {
		storageService.addFolder((long)6, "22sssd23",(long)11);
	}
	
	@Test
	public void testsql() throws Exception {
		storageService.selectOtherList((long)1, "", 1, 1, 10);
	}
}
