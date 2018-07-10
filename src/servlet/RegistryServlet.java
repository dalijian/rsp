package servlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.service.factory.ServiceFactory;
import shopping.vo.Login;

/**
 * 用户注册的Servlet
 */
public class RegistryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("UTF-8");
			// 1.接收参数
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String telephone = request.getParameter("telephone");
			// 2.封装数据
			Login user = new Login();
			user.setId(""+new Date());
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setName(name);
			user.setSex(sex);
			user.setTelephone(telephone);
			
			// 3.调用业务层处理数据
			//异常处理放在数据层
			ServiceFactory.getLoginServiceInstance().insert(user);

			// 4.页面跳转
			response.sendRedirect(request.getContextPath()+"/product/login.jsp");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
