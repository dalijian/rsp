package servlet;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/*
 * 使用装饰器模式 处理     request 乱码
 */
public class DecodingRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	public DecodingRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/*
	 * (non-Javadoc)装饰器， 
	 *  HttpServletRequestWrapper 继承HttpServletRequest 将  getParameter封装后
	 *  交给HttpServletRequest调用
	 *  
	 * 
	 * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
	 */
	@Override
	public String getParameter(String name) {
		String method = request.getMethod(); // 获得请求方式 methods post 或者 get
		if (method.equalsIgnoreCase("get")) {
			String str = null;

			try {
				str = new String(request.getParameter(name).getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;

		} else if (method.equalsIgnoreCase("post")) {
			try {
				request.setCharacterEncoding("utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	return super.getParameter(name);
	}

}
