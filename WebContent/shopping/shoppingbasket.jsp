<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结账</title>
</head>
<body>
<h1>购物车列表</h1>

<%
	Map<String,Integer> map = (Map<String,Integer>)request.getSession().getAttribute("shoppingBasket");
	for(String name:map.keySet()){
		Integer count = map.get(name);
%>
    <h4>您购买的商品是:<%=name %>, 对应购买的数量:<%= count %> </h4>
    
<%
	}
	
	
%>
<a target="_blank" href="/myweb/ProductListServlet">查看全部商品信息</a>
</body>
</html>