package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shopping.service.factory.ServiceFactory;
import shopping.vo.User;

class UserServletTest {

	@Test
	void testRegistry() {
		fail("Not yet implemented");
	}

	@Test
	void testLogin() {
		fail("Not yet implemented");
	}

	@Test
	void testQuit() {
		fail("Not yet implemented");
	}

	@Test
	void testCheckUsername() {
		User vo = new User();
		vo.setUsername("lijian");
		try {
			User result = ServiceFactory.getUserServiceInstance().checkUsername(vo);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
