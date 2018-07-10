package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shopping.service.factory.ServiceFactory;
import shopping.vo.PageBean;
import utils.BaseServlet;

@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	/**
	 * 查询热门商品
	 * @param request
	 * @param response
	 * @return
	 */
	public String findHotAll(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String   str= request.getParameter("is_hot");
		Integer is_hot = Integer.parseInt(str);
		/*
		 * 由于pagebean 没有 热门商品，和最新商品，所以无法用pagebean封装查询，返回结果也不要分页所以 是否用pagebean返回作用不大，
		 * 就直接有String 类型 categoryName查询数据库
		 * 而且数据库没有pagebean表，所以DBUtils不能直接将查询结果封装成pagebean，还是需要我们手动封装，所以把pagebean作为页面通用查询载体，作用不大
		 */
		try {
			PageBean pagebean = ServiceFactory.getProductSeriveInstance().findHotAll(is_hot);
			String json = new Gson().toJson(pagebean);
			response.getWriter().append(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询最新商品
	 * @param request
	 * @param response
	 * @return
	 */
	public String findNewAll(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			PageBean pagebean = ServiceFactory.getProductSeriveInstance().findNewAll();
			String json = new Gson().toJson(pagebean);
			response.getWriter().append(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 商品分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	public String findPageAll(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			String cid = request.getParameter("cid");
			String currentPage = request.getParameter("currentPage");
		PageBean pageBean =ServiceFactory.getProductSeriveInstance().findPageAll(cid,currentPage);
			String json = new Gson().toJson(pageBean);
			response.getWriter().append(json);
		}catch(Exception e) {
			e.printStackTrace();	
		}return null;
	}

}
