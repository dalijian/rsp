package servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车的Servlet
 */
public class ShoppingCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = new String(request.getParameter("name").getBytes("iso-8859-1"),"utf-8");
		//判断session是否为空																		
		Map<String,Integer> map = (Map<String, Integer>) request.getSession().getAttribute("shoppingBasket");
		
		
		if(map == null){
			map = new LinkedHashMap<String,Integer>();
		}
		
		
		if(map.containsKey(name)){
	
			Integer count = map.get(name);
			
			map.put(name, count++);
		}else{
			
			map.put(name, 1);
		}
//		
		
		
		request.getSession().setAttribute("shoppingBasket", map);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("<h3><a href='/myweb/product/product_list.jsp'>继续购物</a> | <a href='/myweb/shopping/shoppingbasket.jsp'>去结算</a></h3>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
