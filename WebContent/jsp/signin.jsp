<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Inspiration-登录</title>

<link rel="shortcut icon" href="/ins/images/gt_favicon.png">

<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="/ins/css/bootstrap.min.css">
<link rel="stylesheet" href="/ins/css/font-awesome.min.css">

<!-- Custom styles for our template -->
<link rel="stylesheet" href="/ins/css/bootstrap-theme.css"
	media="screen">
<link rel="stylesheet" href="/ins/css/main.css">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="/ins/js/html5shiv.js"></script>
	<script src="/ins/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	<c:if test="${!empty requestScope.msgcode }">
		<%
		int msgcode = Integer.parseInt(request.getAttribute("msgcode").toString());
		switch(msgcode){
		case 101:
			out.print("<script>alert('账号或密码错误，登录失败！');</script>");
			break;
		case 103:
			out.print("<script>alert('当前账号已经登录！');</script>");
			break;
		default:
			break;
		}
	%>
	</c:if>
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="/ins/jsp/index.jsp">主页</a></li>
			<li class="active">用户登录</li>
		</ol>

		<div class="row">

			<!-- Article main content -->
			<article class="col-xs-12 maincontent"> <header
				class="page-header">
			<h1 class="page-title">登录</h1>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">请登录</h3>
						<hr>
						<form name="form1" method="post" action="/ins/LoginServlet">
							<div class="top-margin">
								<label>用户名 <span class="text-danger">*</span></label>
								<c:if test="${empty requestScope.username }">
									<input type="text" class="form-control" placeholder="用户名"
										name="username" id="username">
								</c:if>
								<c:if test="${!empty requestScope.username }">
									<input type="text" name="form-control" placeholder="用户名"
										name="username" id="username"
										value="${requestScope.username }">
								</c:if>
							</div>
							<div class="top-margin">
								<label>密码 <span class="text-danger">*</span></label> <input
									type="password" class="form-control" placeholder="密码"
									name="password" id="password">
							</div>

							<hr>

							<div class="row">
								<div class="col-lg-8">
									<b><a href="/ins/jsp/signup.jsp">注册新账号</a></b>
								</div>
								<div class="col-lg-4 text-right">
									<button class="btn btn-action btn-block" type="submit">登录</button>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>

			</article>
			<!-- /Article -->

		</div>
	</div>
	<!-- /container -->








	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="/ins/js/headroom.min.js"></script>
	<script src="/ins/js/jQuery.headroom.min.js"></script>
	<script src="/ins/js/template.js"></script>
</body>
</html>