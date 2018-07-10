package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;
/**
 * download文件夹下载处理
 * 
 * filename_2 是通过方法编码处理 结果是 乱码
 * filename_1 是通过 new String(request.getParamter("filename").getBytes("iso-8859-1"),"utf-8");
 * 
 */


/**
 * Servlet implementation class DownlServlet
 */
@SuppressWarnings("deprecation")
@WebServlet("/Download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置返回响应头
		response.setContentType("text/html;charset=utf-8");
		
		String filename_1 = new String(request.getParameter("filename").getBytes("iso-8859-1"), "UTF-8");
		System.out.println(filename_1);
		
		
		
		//得到servlet上下文
		ServletContext context = getServletContext();
		//设置下载文件类型
		String mineType = context.getMimeType(filename_1);
		response.setHeader("Content-type", mineType);
		
		/*
		 * 根据浏览器的类型处理中文文件的乱码问题
		 */
		String agent = request.getHeader("User-Agent");
		System.out.println(agent);
		String filename_2;
		if (agent.contains("Firefox")) {
			 filename_2 = base64EncodeFileName(filename_1);

		} else {
			 filename_2 = URLEncoder.encode(filename_1, "UTF-8");

		}
		System.out.println("getbytes(utf-8)"+filename_1);
		System.out.println("经过编码处理的filename"+filename_2);
		
//		System.out.println("realpath=="+realPath);
		//设置响应头
		response.setHeader("Content-Disposition", "attachment;filename=" + filename_2);
		//拿到要下载文件的输入流
		InputStream is = context.getResourceAsStream("/download/" + filename_1);
		if(is==null) {
			System.out.println("is ==null");
		}else {
			System.out.println(is);
		}
		//创建响应输出流
		OutputStream out = response.getOutputStream();
		//将输入流赋值给输出流
		IOUtils.copy(is, out);
		//关闭流
		out.close();
		is.close();

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
	public static String base64EncodeFileName(String fileName) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try {
			return "=?UTF-8?B?"
					+ new String(base64Encoder.encode(fileName
							.getBytes("UTF-8"))) + "?=";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
