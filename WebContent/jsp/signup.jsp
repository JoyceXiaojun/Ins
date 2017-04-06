<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Inspiration-注册</title>

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
<script type="text/javascript">
	function check() {
		if (document.form1.username.value == "") {
			document.getElementById("username").innerHTML = "<font color=red>请输入用户名</font>";
			document.form1.username.focus();
			alert('用户名不能为空！');
			return false;
		} else if (document.form1.password.value == "") {
			alert('密码不能为空！');
			return false;
		} else if (document.form1.pwconfirm.value == "") {
			alert('请再输一次密码！');
			return false;
		} else if (document.form1.password.value != document.form1.pwconfirm.value) {
			alert('亲！您两次输入的密码不一样！');
			return false;
		} else if (document.form1.email.value == "") {
			alert('亲！请输入一个你常用的邮箱！');
			return false;
		} else if (document.form1.photo.value == "") {
			alert('亲！请选择一张图片作为你的头像！');
			return false;
		}
	}
</script>
</head>

<body>
	<c:if test="${!empty requestScope.msgcode }">
		<%
			int msgcode = Integer.parseInt(request.getAttribute("msgcode")
						.toString());
				switch (msgcode) {
				case 5:
					out.print("<script>alert('亲！请选择正确格式的图片上传！');</script>");
					break;
				case 4:
					out.print("<script>alert('亲！请选择一张图片作为你的头像！');</script>");
					break;
				case 3:
					out.print("<script>alert('亲！您填写的邮箱已注册！');</script>");
					break;
				case 2:
					out.print("<script>alert('亲！您填写的用户名已注册！');</script>");
					break;
				default:
					break;
				}
		%>
	</c:if>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img src="/ins/images/logo.png"
					alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a class="btn" href="/ins/jsp/index.jsp">返回</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="/ins/jsp/index.jsp">首页</a></li>
			<li class="active">用户注册</li>
		</ol>

		<div class="row">

			<!-- Article main content -->
			<article class="col-xs-12 maincontent"> <header
				class="page-header">
			<h2 class="page-title">用户注册</h2>
			</header>

			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-body">
						<h3 class="thin text-center">注册新账号</h3>
						<hr>
						<form name="form1" method="post" enctype="multipart/form-data"
							action="/ins/RegisterServlet" onsubmit="return check()">
							<div class="top-margin">
								<label>用户名 <span class="text-danger">*</span></label> <input type="text" class="form-control"
									name="username" id="username">
							</div>
							<div class="row top-margin">
								<div class="col-sm-6">
									<label>密码 <span class="text-danger">*</span></label> <input
										type="password" class="form-control" name="password"
										id="password">
								</div>
								<div class="col-sm-6">
									<label>确认密码 <span class="text-danger">*</span></label> <input
										type="password" class="form-control" name="pwconfirm"
										id="pwconfirm">
								</div>
							</div>

							<div class="top-margin">
								<label>邮箱 <span class="text-danger">*</span></label> <input
									type="text" class="form-control" name="email" id="email">
							</div>

							<div class="top-margin">
								<label>选择头像 <span class="text-danger">*</span> </label> <input type="file" name="photo" id="photo">
							</div>

							<div class="top-margin">
								<label>个性签名</label>
								<textarea name="mark" id="mark" class="form-control"
									placeholder="请输入您的个性签名" rows="4"></textarea>
							</div>

							<hr>
							<div class="top-margin" align="center">
								
								<button class="btn btn-action" type="submit"
								 name="submit" onClick="return check()">注册</button>
								<button class="btn" type="reset">重置</button>
							</div>
						</form>
						<a data-toggle="modal" data-target="#myModal">用户协议</a>
								<!-- Modal -->
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">
													<span aria-hidden="true">&times;</span> <span
														class="sr-only">Close</span>
												</button>
												<h4 class="modal-title" id="myModalLabel">用户协议</h4>
											</div>
											<div class="modal-body">这里是我们的用户协议</div>
											<div class="modal-footer">
												<button type="button" class="btn"
													data-dismiss="modal">关闭</button>
											</div>
										</div>
									</div>
								</div>
					</div>
				</div>

			</div>

			</article>
			<!-- /Article -->

		</div>
	</div>
	<!-- /container -->

	<footer id="footer" class="top-space">

	<div class="footer1">
		<div class="container">
			<div class="row">

				<div class="col-md-3 widget">
					<h3 class="widget-title">联系我们</h3>
					<div class="widget-body">
						<p>
							+234 23 9873237<br> <a href="mailto:#">some.email@somewhere.com</a><br>
							<br>
							天津市塘沽开发区
						</p>
					</div>
				</div>

				<div class="col-md-3 widget">
					<h3 class="widget-title">关注我们</h3>
					<div class="widget-body">
						<p class="follow-me-icons clearfix">
							<a href=""><i class="fa fa-twitter fa-2"></i></a> <a href=""><i
								class="fa fa-dribbble fa-2"></i></a> <a href=""><i
								class="fa fa-github fa-2"></i></a> <a href=""><i
								class="fa fa-facebook fa-2"></i></a>
						</p>
					</div>
				</div>

				<div class="col-md-6 widget">
					<h3 class="widget-title">关于我们</h3>
					<div class="widget-body">
						<p>我们是......</p>
					</div>
				</div>

			</div>
			<!-- /row of widgets -->
		</div>
	</div>

	<div class="footer2">
		<div class="container">
			<div class="row">

				<div class="col-md-6 widget">
					<div class="widget-body">
						<p class="simplenav">
							<a href="#">Home</a> | <a href="jsp/Home.html">About</a> | <a
								href="sidebar-right.html">Sidebar</a> | <a href="contact.html">Contact</a>
							| <b><a href="jsp/SignUp.html">Sign up</a></b>
						</p>
					</div>
				</div>

				<div class="col-md-6 widget">
					<div class="widget-body">
						<p class="text-right">
							Copyright &copy; 2014, Your name. Designed by <a
								href="http://gettemplate.com/" rel="designer">gettemplate</a>
						</p>
					</div>
				</div>

			</div>
			<!-- /row of widgets -->
		</div>
	</div>
	</footer>





	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="/ins/js/jquery.min.js"></script>
	<script src="/ins/js/bootstrap.min.js"></script>
	<script src="/ins/js/headroom.min.js"></script>
	<script src="/ins/js/jQuery.headroom.min.js"></script>
	<script src="/ins/js/template.js"></script>
</body>
</html>