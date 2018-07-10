<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="./jquery/jquery-1.8.3.min.js"></script>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <script src="./js/bootstrap.min.js"></script>
    <title>商品列表</title>

    <style>

        td {
            width: 200px;
        }

        form {
            margin: 0 auto;

        }

        .productList {
            width: 1263px;
            margin: 0 auto;
        }

        a {
            text-decoration: none;
        }

    </style>

    <script>
        $(function () {
            <%--alert( ${requestScope.delete});--%>
            // alert("helloworld")
            $("tr:odd").css("backgroundColor", "#eeeeee");
            $("tr:even").css("backgroundColor", "#cccccc")
        });

    </script>
</head>
<body>
<h1 class="text-center">商品列表页面</h1>

<form action="${pageContext.request.contextPath}/SelectServlet" class="form-horizontal" method="post">
    <div class="form-group">
        <select name="column" id="column" class="col-lg-6 col-lg-offset-2 input-sm">
            <option value="pid">商品编号</option>
            <option value="pname" selected>商品名称</option>
            <option value="is_hot">是否热门</option>
            <option value="shop_price">商场价</option>
            <option value="market_price">市场价</option>
        </select>
    </div>
    <div class="form-group ">
        <label for="search" class="col-lg-2 control-label">查询关键字</label>
        <input type="search" name="keyWord" id="search" placeholder="请输入查询字段" class="col-lg-6">
    </div>
    <div class="form-group">
        <label for="currentPage" class="col-lg-2 control-label">查询页面</label>
        <input class="col-lg-2 input-sm" type="text" id="currentPage" name="currentPage">
    </div>
    <div class="form-group">
        <p class="class-help col-lg-offset-2">每页显示记录数</p>
        <input type="number" name="lineSize" min="5" max="20" step="5" value="10" class="col-lg-2 col-lg-offset-2"/>
    </div>
    <input type="submit" value="tijiao" class="col-lg-offset-2 col-lg-2">
</form>


<hr>
<a href="${pageContext.request.contextPath}/product/newProduct.jsp" id="insert_a" class="glyphicon">添加商品</a>
<hr>
<div class="productList">
    <table border="1" width="100%" class="table-bordered">

        <tr>
            <td>商品编号</td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>是否热门</td>
            <td>商品描述</td>
            <td>操作</td>
        </tr>


        <c:forEach var="p" items="${list}">

        <tr>
            <td>${ p.pid }</td>
            <td>${ p.pname }</td>
            <td>${ p.shop_price }</td>
            <td>
                <c:if test="${ p.is_hot == 1 }">
                    是
                </c:if>
                <c:if test="${ p.is_hot != 1 }">
                    否
                </c:if>
            </td>
            <td>${ p.pdesc }</td>
            <td id="change"><a href="${pageContext.request.contextPath}/ProductDeleteServlet?pid=${p.pid}">删除</a>||
                <a href="${pageContext.request.contextPath}/product/newProduct.jsp?pid=${p.pid}&pname=${p.pname}">修改</a></td>
        </tr>
        </c:forEach>
</div>
</table>
</body>
</html>