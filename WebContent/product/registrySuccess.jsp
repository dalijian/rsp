<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>注册成功</title>

    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="css/style.css" type="text/css"/>

    <script src="../javaScript/jquery-2.1.0.min.js"></script>
    <script src="../javaScript/jquery.validate.js"></script>
    <script src="../javaScript/bootstrap.min.js"></script>
    <script src="../javaScript/messages_zh.js"></script>
    <style>
        body{
            width: 1263px;

        }
    </style>
    <script>
        $(".foot").load("foot.html");
    </script>
</head>


<div id="hidden">
    <h1></h1>
</div>
<div class="container-fluid">
    <div class="col-md-4">
        <img src="img/logo2.png"/>
    </div>
    <div class="col-md-5">
        <img src="img/header.png"/>
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <li><a href="login.jsp">登录</a></li>
            <li><a href="register.htm">注册</a></li>
            <li><a href="cart.htm">购物车</a></li>
        </ol>
    </div>
</div>
<!--
    时间：2015-12-30
    描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">手机数码<span class="sr-only">(current)</span></a></li>
                    <li><a href="#">电脑办公</a></li>
                    <li><a href="#">电脑办公</a></li>
                    <li><a href="#">电脑办公</a></li>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>

<div class="contain">
    <h1><% request.getAttribute("msg");%></h1>
    <h1>请查收邮件，激活邮件</h1>
    <div class="row col-lg-4 col-lg-offset-2" >
        <h1><a href="${pageContext.request.contextPath}/product/index.jsp">首页</a></h1>

    </div>
    <div class="row col-lg-4 col-lg-offset-2">
        <h1><a href="${pageContext.request.contextPath}/product/login.jsp">登录 </a></h1>
    </div>
    <div class="row col-lg-4 col-lg-offset-2">
        <h1><a href="${pageContext.request.contextPath}/product/registry.html"> 注册</a></h1>
    </div>
</div>

<div class="foot"></div>

</body>
</html>
