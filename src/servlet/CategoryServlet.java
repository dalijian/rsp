package servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shopping.service.factory.ServiceFactory;
import shopping.vo.Category;
import utils.BaseServlet;

public class CategoryServlet  extends BaseServlet{
	public String findAll(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			List<Category> list = ServiceFactory.getICategoryServiceInstance().findAll();
			String json = new Gson().toJson(list);
			response.getWriter().append(json);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
