<%@page import="com.kc.inspiration.po.User"%>
<%@page import="com.kc.inspiration.biz.impl.UserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IUserBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Inspiration-个人主页</title>

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
				<a class="navbar-brand" href="/ins/jsp/index.jsp"><img src="/ins/images/logo.png"
					alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-left" style="margin-left: 80px">
					<li><a href="/ins/jsp/home.jsp">个人主页</a></li>
					<li class="dropdown" class="active"><a href=""
						class="dropdown-toggle" data-toggle="dropdown">我的INS<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/ins/jsp/publish.jsp">我要发布</a></li>
							<li><a href="/ins/jsp/auctionapplication.jsp">我要拍卖</a></li>
							<li><a href="/ins/jsp/auction.jsp">我的交易</a></li>
							<li><a href="/ins/jsp/personal.jsp">我的作品</a></li>
							<li><a href="/ins/jsp/praise.jsp">我赞过的</a></li>
							<li><a href="/ins/jsp/collect.jsp">我的收藏</a></li>
							<li><a href="/ins/jsp/focus.jsp">我的关注</a></li>
						</ul></li>

					<li><a href="">好友</a></li>
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown">消息小站<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/ins/jsp/message.jsp">私信</a></li>
							<li><a href="">公开评论</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav pull-right">
					<li>
						<form class="navbar-form navbar-left">

							<input type="text" class="form-control" placeholder="Search...">

							<a class="btn btn-sm" type="submit" style="width: 5px"><i
								class="icon-search"></i></a>
						</form>
					</li>
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown">账号<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/ins/jsp/information.jsp">账户信息</a></li>
							<li><a href="/ins/jsp/signin.jsp">切换账号</a></li>
							<li><a href="/ins/LogoutServlet">注销</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
			<div class="navbar-collapse collapse"></div>
		</div>
	</div>
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>
	<%
		IUserBiz userBiz = new UserBizImpl();
		User user = userBiz.findById(Integer.parseInt(request
				.getParameter("uid")));
	%>
	<!-- container -->
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="/ins/jsp/home.jsp">我的INS</a></li>
			<li class="active">用户信息</li>
		</ol>

		<div class="row">
			<!-- main content -->
			<div class="col-sm-9">
				<header class="page-header">
				<h2 class="page-title">
					<strong>个人资料</strong>
				</h2>
				</header>
				<div class="row">
				<p>
				<img src="/ins/upload/images/photo/<%=user.getPhoto() %>" alt="头像" width="200">
				</p>
				<p>
				<label>用户名</label><%=user.getUsername() %>
				</p>
				<p>
				<label>Email</label><%=user.getEmail() %>
				</p>
				<p>
				<label>注册日期</label><%=user.getRegistertime() %>
				</p>
				<p>
				<label>用户等级</label><%=user.getLevel() %>
				</p>
				<p>
				<label>诚信度</label><%=user.getHonestylevel() %>
				<p>
				<label>用户状态</label><%=user.getStatus() %>
				</p>
				<p>
				<label>账户状态</label><%=user.getAccstatus() %>
				</p>
				<p>
				<label>关注</label><%=user.getAttentioncount() %>
				</p>
				<p>
				<label>粉丝</label><%=user.getFanscount() %>
				</p>
				<label>个性签名</label>
				<p><%=user.getMark() %></p>
				</div>
				<!-- /main content -->
			</div>
			<div class="col-sm-1"></div>
			<!-- Sidebar -->
				<aside class="col-sm-2 sidebar sidebar-right">

				<div class="widget">
					<img alt="头像" src="/ins/upload/images/photo/${sessionScope.user.photo }" class="img-circle"
						width="100" height="100">
					<h2>
						<strong>${sessionScope.user.username }</strong>
					</h2>
					<ul class="list-unstyled list-spaces">
						<li><i class="fa fa-user fa-lg fa-fw"></i><a href="">
								个人资料 </a><br></li>
						<li><i class="fa fa-star fa-lg fa-fw"></i><a href="">
								个人收藏</a><br></li>
						<li><i class="fa fa-thumbs-up fa-lg fa-fw"></i><a href="">
								赞过的作品</a><br></li>
						<li><i class="fa fa-heart fa-lg fa-fw"></i><a href="">
								已关注的人</a><br></li>
						<li><i class="fa fa-gavel fa-lg fa-fw"></i><a href="">
								买卖情况</a><br></li>
					</ul>
				</div>

				</aside>
				<!-- /Sidebar -->

			</div>
		</div>
		<!-- /container -->


		<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">

					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>
								+234 23 9873237<br> <a href="mailto:#">some.email@somewhere.com</a><br>
								<br> 234 Hidden Pond Road, Ashland City, TN 37015
							</p>
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">Follow me</h3>
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
						<h3 class="widget-title">Text widget</h3>
						<div class="widget-body">
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Excepturi, dolores, quibusdam architecto voluptatem amet fugiat
								nesciunt placeat provident cumque accusamus itaque voluptate
								modi quidem dolore optio velit hic iusto vero praesentium
								repellat commodi ad id expedita cupiditate repellendus possimus
								unde?</p>
							<p>Eius consequatur nihil quibusdam! Laborum, rerum, quis,
								inventore ipsa autem repellat provident assumenda labore soluta
								minima alias temporibus facere distinctio quas adipisci nam sunt
								explicabo officia tenetur at ea quos doloribus dolorum voluptate
								reprehenderit architecto sint libero illo et hic.</p>
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
								<a href="#">Home</a> | <a href="about.html">About</a> | <a
									href="sidebar-right.html">Sidebar</a> | <a href="contact.html">Contact</a>
								| <b><a href="signup.html">Sign up</a></b>
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