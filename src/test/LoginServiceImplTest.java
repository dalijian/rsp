package test;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;

import org.junit.jupiter.api.Test;

import shopping.Dao.factory.DaoFactory;
import shopping.vo.Login;

class LoginServiceImplTest {

	@Test
	void testInsert() throws Exception {
		Login vo = new Login();
		vo.setBirthday(new Date());
		vo.setEmail("2212245847@qq.com");
		vo.setUsername("lijian");
		vo.setTelephone("1234");
		vo.setSex("男");
		vo.setPassword("123");
		vo.setName("lijian");
		vo.setId(""+new Date());
		boolean result = DaoFactory.getILoginDaoInstance().doCreate(vo);
		System.out.println(result);
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testList() {
		fail("Not yet implemented");
	}

	@Test
	void testListIntegerIntegerStringString() {
		fail("Not yet implemented");
	}

	@Test
	void testLogin() {
		fail("Not yet implemented");
	}
	@Test
	void add() {
		Login vo = new Login();
		vo.setBirthday(new Date());
		vo.setEmail("2212245847");
		vo.setId(new Date().getTime()+"");
		vo.setUsername("username");
		vo.setPassword("password");
		vo.setTelephone("1888888888");
		vo.setSex("男");
		vo.setName("lijian");
		vo.setHobby("football");
		DaoFactory.getILoginDaoInstance().add(vo);
		
	}

}
