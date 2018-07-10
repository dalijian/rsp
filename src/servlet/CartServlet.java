package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import shopping.service.factory.ServiceFactory;
import shopping.vo.Cart;
import shopping.vo.CartItem;
import shopping.vo.Product;
import utils.BaseServlet;

public class CartServlet extends BaseServlet {
	public String addCart(HttpServletRequest req,HttpServletResponse resp){
		// 接收参数:
		try{
			String pid = req.getParameter("pid");
			Integer count = Integer.parseInt(req.getParameter("count"));
			// 封装CartItem:
			CartItem cartItem = new CartItem();
			cartItem.setCount(count); //保存 商品数量
			
			Product product = ServiceFactory.getProductSeriveInstance().findByPid(pid); //根据主键查找商品信息
			System.out.println("product-->"+product);
			product.setPid(pid);
			cartItem.setProduct(product);  // 向购物相中  存放  商品
			// 调用Cart中的方法处理
			Cart cart = getCart(req);		//从session 中得到 购物车
			cart.addCart(cartItem);			//向购物车中增加购物项
			// 页面跳转
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 删除购物车
	 * 清空购物车的执行的方法:
	 * @param req
	 * @return
	 */
	public String clearCart(HttpServletRequest req,HttpServletResponse response){
		try{response.setContentType("html/text;charset=utf-8");
			// 调用Cart中的clearCart的方法:
			Cart cart = getCart(req);
			cart.clearCart(); //清除购物车中的 购物项 集合
			// 页面跳转：
			response.getWriter().append(new Gson().toJson("清空购物车成功"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从购物车中移除购物项的方法:removeCart
	 * @param req
	 * @return
	 */
	public String removeCart(HttpServletRequest request,HttpServletResponse response){
		try{response.setContentType("html/text;charset=utf-8");
			// 接收参数:
			String pid = request.getParameter("pid");
			// 调用Cart中的clearCart的方法:
			Cart cart = getCart(request);
			cart.removeCart(pid);
			// 页面跳转：
			String json = new Gson().toJson("删除成功");
			response.getWriter().append(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
// 在 session 中保存  cart  购物车  属性
	private Cart getCart(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		return cart;
	}
	
	//ajax 请求得到cart数据
	public void getCart(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("html/text;charset=utf-8");
		
		String json = new Gson().toJson(getCart(request));
		System.out.println(json);
		try {
			response.getWriter().append(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
