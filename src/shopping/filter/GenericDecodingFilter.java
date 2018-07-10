package shopping.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/** 使用动态代理 增强方法
 * Servlet Filter implementation class GenieDecodingFilter
 */
@WebFilter("/*")
public class GenericDecodingFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public GenericDecodingFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		final HttpServletRequest req = (HttpServletRequest) request;
		
		//InvocationHandler 是代理实例的调用处理程序 实现的接口。
		//object invoke(object proxy,Method method,Object[] args)
		//在代理实例上处理方法调用并返回结果。在与方法关联的代理实例上调用方法时，将在调用处理程序上调用此方法， 返回从代理实例的方法调用返回的值
		
		
		//返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序
		/*相当于
		 *  Proxy.getProxyClass(loader, interfaces).
         getConstructor(new Class[] { InvocationHandler.class }).
         newInstance(new Object[] { handler });
         
         
         	public static Class<?> getProxyClass (ClassLoader loader,Class<?> ...interfaces)返回代理类的 java.lang.Class 对象，并向其提供类加载器和接口数组。
		 */
	final	HttpServletRequest  selfReq = (HttpServletRequest) Proxy.newProxyInstance( //获得代理对象
				req.getClass().getClassLoader()//使用被代理对象类加载器，载入被代理对象Class对象
				, req.getClass().getInterfaces()// 代理类实现了 被代理对象的接口，从而实现与被代理类方法 相同
				, new InvocationHandler() {//new InvocationHandler () 使用了匿名内部类 ， 是代理对象处理被代理对象的处理类
		
			
		
			@Override
			public Object invoke(Object arg0, //代理对象
					Method method,// 被代理对象调用接口方法的Method对象， 方法必须是被代理对象实现接口的方法
					Object[] arg2) //包含传入代理实例上方法调用的参数值的对象数组，如果接口方法不使用参数，则为 null
							throws Throwable {//处理方法
			
				if(method.getName().equals("getParameter")){   //得到要增强的方法
					String requestType =req.getMethod();		//得到请求类型
					if(requestType.equalsIgnoreCase("get")) {
						String value = (String) method.invoke(req,arg2);//调用方法 
						value = new String (value.getBytes("iso-8859-1"),"utf-8");
						return value;
					}
					if("post".equalsIgnoreCase(requestType)){
				req.setCharacterEncoding("utf-8");
					}
				}
				return method.invoke(req,arg2);
				
			}
				});
		chain.doFilter(selfReq, response);
		
	}
		
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
