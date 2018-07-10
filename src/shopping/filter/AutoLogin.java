package shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import shopping.service.factory.ServiceFactory;
import shopping.vo.Login;
import utils.CookieUtils;

/**
 * Servlet Filter implementation class AutoLogin
 */
@WebFilter("/*")
public class AutoLogin implements Filter {
	
    /**
     * Default constructor. 
     */
    public AutoLogin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	if( session.getAttribute("existLogin")!=null) {
		chain.doFilter(req, response);
		return ;
	}
	
	Cookie[] cookies =  req.getCookies();
	
	if(CookieUtils.findCookie(cookies, "login")==null) {
		chain.doFilter(req, response);
		return;
	}
	String result = CookieUtils.findCookie(cookies, "login").getValue();
	String username = result.split("#")[0];
	String password = result.split("#")[1];
	Login vo = new Login();
	vo.setUsername(username);
	vo.setPassword(password);
	if(!ServiceFactory.getLoginServiceInstance().login(vo)) {
		chain.doFilter(req, response);
		return;
	}
	session.setAttribute("existLogin",username+"#"+password);
	
		// pass the request along the filter chain
		chain.doFilter(req, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
