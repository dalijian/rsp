<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<% String path = request.getContextPath(); %>
<% if( request.getParameter("pname")!=null) {
    String pname = new String(request.getParameter("pname").getBytes("iso-8859-1"), "utf-8");
    request.setAttribute("pname", pname);
}
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新商品</title>

    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <script src="../jquery/jquery-2.1.0.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script>
        $(function () {
                <%--alert("${requestScope.pname}")--%>
            <%--if (${requestScope.pid==null}||${requestScope.pname==null}) {--%>
            if (${param.pid==null}) {
                $("h2").text("添加新商品");
                $("#pid").val("" + new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());


            }else {
            $("#pname").val("${requestScope.pname}");
            $("#pid").val("${param.pid}");
            $("h2").text("修改商品");

        }
        $("#pdate").val("" + new Date().getFullYear() + "-" + (new Date().getMonth() + 1) + "-" + new Date().getDate());
        });
    </script>
    <style>
        body{
            width:1263px;
            margin: 0 auto;
        }
    </style>


</head>
<body>
<h2 class="text-center"></h2>

<form action="${pageContext.request.contextPath}/InsertServlet" class="form-horizontal" method="post">
    <div class="form-group">
        <label for="pid" class="col-lg-2 control-label" hidden>pid</label>
        <input class="col-lg-6" type="text" name="pid" id="pid" hidden>
    </div>
    <div class="form-group">
        <label for="pname" class="col-lg-2 control-label">商品名</label>
        <input class="col-lg-6" type="text" name="pname" id="pname" class="form-control">
    </div>
    <div class="form-group">
    <label for="market_price" class="col-lg-2 control-label">市场价</label>
    <input class="col-lg-6" type="text"
           id="market_price" name="market_price"
           class="form-control">
</div>
    <div class="form-group">
        <label for="shop_price" class="col-lg-2 control-label">卖场价</label>
        <input class="col-lg-6" type="text" id="shop_price" name="shop_price" class="form-control">
    </div>
    <div class="form-group">
        <label for="pimage" class="col-lg-2 control-label">图片链接地址</label>
        <input class="col-lg-6" type="text" id="pimage" name="pimage" class="form-control">
    </div>
    <div class="form-group">
        <label for="pdate" class="col-lg-2 control-label">添加商品日期，默认当前日期</label>
        <input class="col-lg-6" type="text" id="pdate" name="pdate" class="form-control">
    </div>
    <div class="form-group">
        <label for="is_hot" class="col-lg-2 control-label">是否热门</label>
        <input class="col-lg-6" type="text" id="is_hot" name="is_hot" class="form-control">
    </div>
    <div class="form-group">
        <label for="pdesc" class="col-lg-2 control-label">商品描述</label>
        <textarea name="pdesc" id="pdesc" cols="10" rows="3" class="col-lg-6"></textarea>
    </div>
    <div class="form-group">
        <label for="pflag" class="col-lg-2 control-label">pflag</label>
        <input class="col-lg-6" type="text" id="pflag" name="pflag" class="form-control">
    </div>

        <input type="submit" value="提交" class="btn col-lg-6 col-lg-offset-2">

</form>
</body>
</html>