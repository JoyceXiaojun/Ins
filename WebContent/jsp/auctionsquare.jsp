<%@page import="com.kc.inspiration.biz.impl.ResourceBizImpl"%>
<%@page import="com.kc.inspiration.biz.IResourceBiz"%>
<%@page import="com.kc.inspiration.po.Auction"%>
<%@page import="com.kc.inspiration.biz.impl.AuctionBizImpl"%>
<%@page import="com.kc.inspiration.biz.IAuctionBiz"%>
<%@page import="com.kc.inspiration.po.User"%>
<%@page import="com.kc.inspiration.po.Work"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.biz.impl.WorkBizImpl"%>
<%@page import="com.kc.inspiration.biz.IWorkBiz"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Inspiration-我的作品</title>

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
	<c:if test="${empty sessionScope.user }">
		<script>
			alert("亲！对不起，请先登录，登录后才能查看拍卖广场!");
			location = "/ins/jsp/signin.jsp";
		</script>
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
				<a class="navbar-brand" href="/ins/jsp/index.jsp"><img src="/ins/images/logo.png"
					alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-left" style="margin-left: 80px">
					<li><a href="/ins/jsp/home.jsp">首页</a></li>
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
					<li><a href="/ins/jsp/auctionsquare.jsp">拍卖广场</a></li>
					<li><a href="/ins/jsp/focus.jsp">好友</a></li>
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

							<input type="text" class="form-control" placeholder="找人、作品、图片...">

							<a class="btn btn-sm" href="/ins/jsp/search.jsp" type="submit" style="margin-top:0;"><i
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

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="/ins/jsp/home.jsp">首页</a></li>
			<li class="active">拍卖广场</li>
		</ol>

		<div class="row">
			<header class="page-header">
			<h2 class="page-title">
				<strong>拍卖广场</strong>
			</h2>
			</header>
			<!-- main content -->
			<div class="col-md-9">
				<h3>拍卖进行时...</h3>
				<div class="row">
				<%
				IAuctionBiz auctionBiz=new AuctionBizImpl();
				List<Auction> alst=auctionBiz.findAuctionByStatus(2);
				IWorkBiz workBiz=new WorkBizImpl();
				IResourceBiz resourceBiz = new ResourceBizImpl();
				if(alst==null||alst.size()<=0){
					System.out.println("还没有任何作品正在拍卖！");
					%>
					<p>还没有任何作品正在拍卖！</p>
					<% 
				}else{
					System.out.println("正在拍卖"+alst.size()+"件作品！");
					Auction auc;
				for(int i=0;i<3&&i<alst.size();i++){
					auc=alst.get(i);
					Work work=workBiz.findById(auc.getWid());
				%>
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt="作品图片" src="/ins/upload/images/works/<%=resourceBiz.findById(work.getWid()).getPath() %>">
							<div class="caption">
								<h3><%=work.getWorkname() %></h3>
								<label>发布时间：<%=work.getPublishtime() %></label>
								<p><label>作品描述：</label><strong><%=work.getWorkdescription() %></strong></p>
								<p><label>底价:</label><strong>￥<%=auc.getBaseprice() %></strong></p>
								<p><label>当前最高价：</label><strong>￥<%=auc.getTopprice() %></strong></p>
								<p><label>开始时间：</label><strong><%=auc.getBegintime() %></strong></p>
								<p><label>结束时间：</label><strong><%=auc.getEndtime() %></strong></p>
								<p>
									<a class="btn btn-primary btn-xs" href="/ins/jsp/bid.jsp?aid=<%=auc.getAid() %>">竞拍</a> <a
										class="btn btn-xs" href="#">分享</a>
								</p>
							</div>
						</div>
					</div>
					<%}
				}
				%>
				</div>
				<div class="row">
					<a class="btn btn-primary" href="#" style="float: right">查看更多</a>
				</div>
				
				<h3>拍卖将来时...</h3>
				<div class="row">
				<%
				alst=auctionBiz.findAuctionByStatus(1);
				if(alst==null||alst.size()<=0){
					%>
					<p>还没有任何作品即将拍卖！</p>
					<% 
				}else{
					Auction auc;
				for(int i=0;i<3&&i<alst.size();i++){
					auc=alst.get(i);
					Work work=workBiz.findById(auc.getWid());
				%>
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt="作品图片" src="/ins/upload/images/works/<%=resourceBiz.findById(work.getWid()).getPath() %>">
							<div class="caption">
								<h3><%=work.getWorkname() %></h3>
								<p><label>发布时间：</label><strong><%=work.getPublishtime() %></strong></p>
								<p><label>作品描述：</label><strong><%=work.getWorkdescription() %></strong></p>
								<p><label>底价:</label><strong>￥<%=auc.getBaseprice() %></strong></p>
								<p><label>开始时间：</label><strong><%=auc.getBegintime() %></strong></p>
								<p><label>结束时间：</label><strong><%=auc.getEndtime() %></strong></p>
								<p>
									<a class="btn btn-primary btn-xs" href="/ins/jsp/work.jsp?wid=<%=work.getWid() %>">浏览</a> <a
										class="btn btn-xs" href="#">分享</a>
								</p>
							</div>
						</div>
					</div>
					<%}
				}
					%>
				</div>
				<div class="row">
				<a class="btn btn-primary" href="#" style="float: right">查看更多</a>
				</div>
				
				<h3>拍卖过去时...</h3>
				<div class="row">
				<%
				alst=auctionBiz.findAuctionByStatus(3);
				if(alst==null||alst.size()<=0){
					%>
					<p>还没有任何作品结束拍卖！</p>
					<% 
				}else{
					Auction auc;
				for(int i=0;i<3&&i<alst.size();i++){
					auc=alst.get(i);
					Work work=workBiz.findById(auc.getWid());
				%>
					<div class="col-md-4">
						<div class="thumbnail">
							<img alt="作品图片" src="/ins/upload/images/works/<%=resourceBiz.findById(work.getWid()).getPath() %>">
							<div class="caption">
								<h3><%=work.getWorkname() %></h3>
								<p><label>发布时间：</label><strong><%=work.getPublishtime() %></strong></p>
								<p><label>作品描述：</label><strong><%=work.getWorkdescription() %></strong></p>
								<p><label>底价：</label><strong>￥<%=auc.getBaseprice() %></strong></p>
								<p><label>最高价：</label><strong>￥<%=auc.getTopprice() %></strong></p>
								<p><label>开始时间：</label><strong><%=auc.getBegintime() %></strong></p>
								<p><label>结束时间：</label><strong><%=auc.getEndtime() %></strong></p>
								<p>
									<a class="btn btn-primary btn-xs" href="/ins/jsp/work.jsp?wid=<%=work.getWid() %>">浏览</a> <a
										class="btn btn-xs" href="#">分享</a>
								</p>
							</div>
						</div>
					</div>
					<%}
				}
					%>
				</div>
				<div class="row">
				<a class="btn btn-primary" href="#" style="float: right">查看更多</a>
				</div>
			</div>
			<!-- /Article -->
			<div class="col-md-1"></div>
			<!-- Sidebar -->
			<aside class="col-md-2 sidebar sidebar-right">

			<div class="list-group">
				<a class="list-group-item" href="/ins/jsp/information.jsp">
						<strong>${sessionScope.user.username }</strong>
				</a> 
				<div class="list-group-item" style="padding:0;">
					<img alt="头像" src="/ins/upload/images/photo/${sessionScope.user.photo }"
					class="">
				</div>
				<div class="list-group-item">
					<p class="list-group-item-text">
					<label>个性签名：</label>
					${sessionScope.user.mark }
					</p>
				</div>
				<a class="list-group-item" href="/ins/jsp/information.jsp">
					<i class="fa fa-user fa-lg fa-fw"></i>个人资料
				</a>
				<a class="list-group-item" href="/ins/jsp/collect.jsp">
					<span class="badge" >14</span><i class="fa fa-star fa-lg fa-fw"></i>个人收藏
				</a>
				<a class="list-group-item" href="/ins/jsp/collect.jsp">
					<span class="badge" >14</span><i class="icon-comments fa-lg fa-fw"></i>消息
				</a>
				<a class="list-group-item" href="/ins/jsp/praise.jsp">
					<span class="badge">14</span><i class="fa fa-thumbs-up fa-lg fa-fw"></i>我赞的作品
				</a>
				<a class="list-group-item" href="/ins/jsp/focus.jsp">
					<span class="badge">14</span><i class="fa fa-heart fa-lg fa-fw"></i>我的关注
				</a>
				<a class="list-group-item" href="/ins/jsp/auction.jsp">
					<span class="badge">14</span><i class="fa fa-gavel fa-lg fa-fw"></i>我的买卖
				</a>
				<a class="list-group-item" href="#"><i class="fa icon-plus fa-lg fa-fw"></i>帮助</a>
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
						<p>我们是来自南开大学的一群大学生...</p>
						
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
							Copyright &copy; 2014, Inspiration. Designed by <a
								href="http://gettemplate.com/" rel="designer">Keen Cloud</a>
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