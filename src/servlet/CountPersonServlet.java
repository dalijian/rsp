package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountServlet
 * 记录登录人数
 * 几率登录时间
 */
@WebServlet("/CountServlet")
public class CountPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 判断cookie是否是第一次
		 */
		Cookie[] cookies = request.getCookies();
		Cookie findCookie= null;
		String username = null;
		for(Cookie i:cookies) {
			if(i.getName().equals("lastVisit")) {
				findCookie= i;
			}
		}
		for(Cookie i:cookies) {
			if(i.getName().equals("username")) {
				username = i.getValue();
			}
		}
		Integer count = (Integer) this.getServletContext().getAttribute("numPerson");
		
//		Cookie cookie = CookieUtils.findCookie(cookies, "lastVist");
		if (findCookie == null) {
			response.getWriter().append("<h1>你是第" + count + "位访客</h1>");

		} else {
			Long L = Long.parseLong(findCookie.getValue());
			Date date = new Date(L);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss:SSS");
			response.getWriter().append("<h1>欢迎"+username+" ,您是第" + count + "位访客! 上次访问时间是:" + sdf.format(date));

		}
		Cookie c = new Cookie("lastVisit", String.valueOf(System.currentTimeMillis()));
		c.setPath("/myweb");
		c.setMaxAge(60 * 60);
		response.addCookie(c);
		response.getWriter().println("<h3><a href='/myweb/product/product_list.jsp'>商品列表</a></h3>");
	

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
