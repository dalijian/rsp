package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopping.service.factory.ServiceFactory;
import shopping.vo.Product;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 模糊查询
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		String column = request.getParameter("column");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Integer lineSize = Integer.parseInt(request.getParameter("lineSize"));
		String keyWord = request.getParameter("keyWord");
		keyWord = new String(keyWord.getBytes("iso-8859-1"), "utf-8");
		System.out.println("keyWord" + keyWord);
		Map<String, Object> map = ServiceFactory.getProductSeriveInstance().list(currentPage,column, lineSize,
				keyWord);
		// TODO Auto-generated method stub
		List<Product> list = (List<Product>) map.get("select");
		System.out.println(list);
		request.setAttribute("list", list);
		RequestDispatcher view = request.getRequestDispatcher("/product/Product_listAll.jsp");
		view.forward(request, response);

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
