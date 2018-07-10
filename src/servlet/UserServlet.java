package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.google.gson.Gson;

import shopping.service.factory.ServiceFactory;
import shopping.vo.User;
import utils.BaseServlet;
import utils.CookieUtils;
import utils.MailUtils;
import utils.UUIDUtils;

public class UserServlet extends BaseServlet {
/*
 * 注册业务
 */
	public String registry(HttpServletRequest request, HttpServletResponse response) {
		try {
			ConvertUtils.register(new DateLocaleConverter(), java.util.Date.class);
			Map<String, String[]> map = request.getParameterMap();
			User vo = new User();
			String code = UUIDUtils.getUUID();
			String uid = UUIDUtils.getUUID();
			BeanUtils.populate(vo, map);
			vo.setCode(code);
			vo.setUid(uid);
			vo.setState(0);
			System.out.println(vo.getDate());
			
			ServiceFactory.getUserServiceInstance().registry(vo);
			MailUtils.sendMail(vo.getEmail(), code, uid);

			// 4.页面跳转
			return "/product/registrySuccess.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/*
 * 检查是否登录
 */
	public String checkLogin(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("html/text;charset=utf-8");
		try {
			String json = new Gson().toJson(request.getSession().getAttribute("existLogin"));
			response.getWriter().append(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
		
	}
	/*
	 * 登录业务
	 */
	public String   login(HttpServletRequest request, HttpServletResponse response) {
		try {response.setContentType("html/text;charset=utf-8");
			User vo = new User();
			Map<String, String[]> map = request.getParameterMap();
			BeanUtils.populate(vo, map);
			// 登录验证
			User result = ServiceFactory.getUserServiceInstance().login(vo);
			if (result == null) {
				String error = "用户名或密码错误";
				String json = new Gson().toJson(error);
				response.getWriter().append(json);
				return null;
			}
			if (result.getState() == 0) {
				String  error = "账户未激活请去邮箱激活";
				String json = new Gson().toJson(error);
				response.getWriter().append(json);
				return null;
			
			}else{
				// 设置会话Session
				HttpSession session = request.getSession();
				session.setAttribute("existLogin",
				request.getParameter("username") + "#" + request.getParameter("password"));
//				 登录成功后 设置cookie 判断key为 login 的cookie是否存在，不存在则设置cookie，
//				 由于session要拿到cookie，实现自动登录所以设置的value 包含username 和password
				//session 名 为 existLogin，    cookie 名 为  login
				if (CookieUtils.findCookie(request.getCookies(), "login") == null) {
					Cookie cookie = new Cookie("login", map.get("username")[0] + "#" + map.get("password")[0]);
					cookie.setMaxAge(30 * 60); // cookie 设置30分钟
					response.addCookie(cookie);
					String json = new Gson().toJson("登录成功");
					response.getWriter().append(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void loginCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("html/text;charset=utf-8");
		HttpSession session = request.getSession();
		session.setAttribute("existLogin",
				request.getParameter("username") + "#" + request.getParameter("password"));
		Cookie cookie = new Cookie("username","lijian");
		response.addCookie(cookie);
		String str = new Gson().toJson( request.getSession().getAttribute("existLogin"));
		response.getWriter().append(str);
	}
/*
 * 退出登录
 */
	public String quit(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("html/text;charset=utf-8");
		request.getSession().removeAttribute("existLogin");
		Cookie cookie = new Cookie("login", "");
		cookie.setMaxAge(0); 
		response.addCookie(cookie);
		String json = new Gson().toJson("退出成功");
		try {
			response.getWriter().append(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
/*
 * ajax 注册时     检查用户名是否存在
 */
	public String checkUsername(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		User vo = new User();
		vo.setUsername(username);
		System.out.println("username:" + username);
		try {
			User result = ServiceFactory.getUserServiceInstance().checkUsername(vo);
			System.out.println(result);
			if (result != null) {
				response.getWriter().append("用户名已存在");
			} else {
				response.getWriter().append("用户名不存在使用");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
/*
 * 邮箱激活码激活
 */
	public String active(HttpServletRequest request, HttpServletResponse response) {
		// 接收激活码：
		try {
			System.out.println(request.getParameterMap());
			String code = request.getParameter("code");
			String uid = request.getParameter("uid");
			System.out.println("code-->" + code);
			System.out.println("uid-->" + uid);
			User vo = new User();
			vo.setCode(code);
			vo.setUid(uid);
			if (ServiceFactory.getUserServiceInstance().active(vo) == null) {
				request.setAttribute("msg", "激活码错误！请重新激活！");
			} else {
				vo.setState(1);
				vo.setCode(null);
				ServiceFactory.getUserServiceInstance().activeEmail(vo);
				request.setAttribute("msg", "激活成功！请去登录！");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "/product/msg.jsp";
	}
}
