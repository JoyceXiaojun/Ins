<%@page import="com.kc.inspiration.biz.impl.UserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IUserBiz"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.po.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>Inspiration-用户信息</title>

	<link rel="shortcut icon" href="/ins/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="/ins/css/bootstrap.min.css">
	<link rel="stylesheet" href="/ins/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="/ins/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="/ins/css/main.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="/ins/js/html5shiv.js"></script>
	<script src="/ins/js/respond.min.js"></script>
	<![endif]-->	
</head>

<body>
	<c:if test="${empty requestScope.lstFollow }">
		<script>window.location="/ins/FindFansServlet";</script>
	</c:if>

	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="/ins/jsp/index.jsp"><img src="/ins/images/logo.png" alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-left" style="margin-left:80px">
					<li><a href="/ins/jsp/home.jsp">首页</a></li>
					<li><a href="/ins/jsp/profile.jsp">个人主页</a></li>
					<li class="dropdown" class="active">
						<a href="" class="dropdown-toggle" data-toggle="dropdown" >我的INS<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/ins/jsp/publish.jsp">我要发布</a></li>
							<li><a href="/ins/jsp/auctionapplication.jsp">我要拍卖</a></li>
							<li><a href="/ins/jsp/auction.jsp">我的交易</a></li>
							<li><a href="/ins/jsp/personal.jsp">我的作品</a></li>
							<li><a href="/ins/jsp/praise.jsp">我赞过的</a></li>
							<li><a href="/ins/jsp/collect.jsp">我的收藏</a></li>
							<li><a href="/ins/jsp/focus.jsp">我的关注</a></li>
						</ul>
					</li>
					<li><a href="/ins/jsp/auctionsquare.jsp">拍卖广场</a></li>
					<li><a href="/ins/jsp/focus.jsp">好友</a></li>
					<li class="dropdown">
						<a href="" class="dropdown-toggle" data-toggle="dropdown" >消息小站<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="/ins/jsp/message.jsp">私信</a></li>
							<li><a href="">公开评论</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav pull-right">
					<li >
						<form class="navbar-form navbar-left" >
						
							<input type="text" class="form-control" placeholder="找人、作品、图片...">
						
							<a class="btn btn-sm" type="submit" style="margin-top:0;"><i class="icon-search"></i></a>
						</form>
					</li>
					<li class="dropdown">
						<a href="" class="dropdown-toggle" data-toggle="dropdown" >账号<b class="caret"></b></a>
						<ul class="dropdown-menu">
						<li><a href="/ins/jsp/information.jsp">账户信息</a></li>
							<li><a href="/ins/jsp/signin.jsp">切换账号</a></li>
							<li><a href="/ins/LogoutServlet">注销</a></li>
						</ul>
					</li>
				</ul>
			</div><!--/.nav-collapse -->
			<div class="navbar-collapse collapse">
			</div>
		</div>
	</div> 
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="/ins/jsp/home.jsp">我的INS</a></li>
			<li class="active">账户信息</li>
		</ol>

		<div class="row">
			<!-- Article main content -->

			<div class="col-sm-9" >
				<header class="page-header">
					<h2 class="page-title"><strong>个人资料</strong></h2>
				</header>
			<div class="row">
			<div class="col-sm-7">
			<form class="form-horizontal" role="form" action="/ins/UpdateInfoServlet" method="post">
				<div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">用户账号</label>
    <div class="col-sm-9">
      <h5>${sessionScope.user.account}</h5>
    </div>
  </div>
			<div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">用户名</label>
    <div class="col-sm-9">
      <input type="name" class="form-control" id="update" name="username" placeholder="Name" value="${sessionScope.user.username }" >
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
    <div class="col-sm-9">
      <input type="email" class="form-control" id="update" name="email" placeholder="Email" value="${sessionScope.user.email }" >
    </div>
  </div>
  <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">注册时间</label>
    <div class="col-sm-9">
   <h5>${sessionScope.user.registertime}</h5>
    </div>
  </div>
 <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">用户等级</label>
    <div class="col-sm-9">
      <h5>${sessionScope.user.level}</h5>
    </div>
  </div>
  <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">诚信度</label>
    <div class="col-sm-9">
      <h5>${sessionScope.user.honestylevel }</h5>
    </div>
  </div>
  <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">在线状态</label>
    <div class="col-sm-9">
      <h5><% 
      User user = (User)session.getAttribute("user");
      if(user.getStatus()==1){
    	  %>在线<%
      }else{
    	  %>离线<%
      }%></h5>
    </div>
  </div>
  <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">账户状态</label>
    <div class="col-sm-9">
      <h5><% 
      if(user.getAccstatus()==1){
    	  %>正常<%
      }else{
    	  %>禁用<%
      }%></h5>
    </div>
  </div>
  <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">关注人数量</label>
    <div class="col-sm-9">
      <h5><%=user.getAttentioncount() %></h5>
    </div>
  </div>
  <div class="form-group">
    <label for="inputname3" class="col-sm-3 control-label">粉丝数量</label>
    <div class="col-sm-9">
      <h5><%=user.getFanscount() %></h5>
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label">个性签名</label>
    <div class="col-sm-9">
      <textarea type="text" class="form-control" id="update" name="mark" >${sessionScope.user.mark }</textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <button type="submit" class="btn btn-default">修改</button>
    </div>
  </div>
</form>
</div>
<div class="col-sm-1"></div>
<div class="col-sm-4" style="border:1px solid">
<div class="row">
<p></p>
<div class="col-sm-12" >
关注人信息<hr>
<table><tr>
	<c:forEach items="${requestScope.lstFollow }" var="follow">
		<td><a href="" >
		<span style="width:60px;height:60px"><img alt="头像" src="/ins/upload/images/photo/${follow.photo }" class="img" width="60" height="60">
		<center><p>${follow.username}</p></center>
		</span>
		</a></td>
	</c:forEach>
</tr></table>
</div>
<div class="col-sm-12">
粉丝信息<hr>
<table><tr>
	<c:forEach items="${requestScope.lstFollower }" var="follower">
		<td><a href="" >
		<span style="width:60px;height:60px"><img alt="头像" src="/ins/upload/images/photo/${follower.photo }" class="img-circle" width="60" height="60">
		<p>${follower.username}</p>
		</span>
		</a></td>
	</c:forEach>
</tr></table>
</div>
</div>
</div>
			</div>			
			</div>
			<!-- /Article -->
			<div class="col-sm-1"></div>
			<% IUserBiz userBiz = new UserBizImpl(); %>
			<!-- Sidebar -->
			<aside class="col-sm-2 sidebar sidebar-right">
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
					<span class="badge" ><%=userBiz.getCollectCount(user.getUid()) %></span><i class="fa fa-star fa-lg fa-fw"></i>个人收藏
				</a>
				<a class="list-group-item" href="/ins/jsp/collect.jsp">
					<span class="badge" >0</span><i class="icon-comments fa-lg fa-fw"></i>消息
				</a>
				<a class="list-group-item" href="/ins/jsp/praise.jsp">
					<span class="badge"><%=userBiz.getPraiseCount(user.getUid()) %></span><i class="fa fa-thumbs-up fa-lg fa-fw"></i>我赞的作品
				</a>
				<a class="list-group-item" href="/ins/jsp/focus.jsp">
					<span class="badge">${sessionScope.user.attentioncount }</span><i class="fa fa-heart fa-lg fa-fw"></i>我的关注
				</a>
				<a class="list-group-item" href="/ins/jsp/focus.jsp">
					<span class="badge">${sessionScope.user.fanscount }</span><i class="fa fa-heart fa-lg fa-fw"></i>我的粉丝
				</a>
				<a class="list-group-item" href="/ins/jsp/auction.jsp">
					<span class="badge">0</span><i class="fa fa-gavel fa-lg fa-fw"></i>我的买卖
				</a>
				<a class="list-group-item" href="#"><i class="fa icon-plus fa-lg fa-fw"></i>帮助</a>
			</div>

			</aside>
			<!-- /Sidebar -->

		</div>
	</div>	<!-- /container -->
	

	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-3 widget">
						<h3 class="widget-title">联系我们</h3>
						<div class="widget-body">
							<p>+234 23 9873237<br>
								<a href="mailto:#">some.email@somewhere.com</a><br>
								<br>
								天津市塘沽开发区
							</p>	
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">关注我们</h3>
						<div class="widget-body">
							<p class="follow-me-icons clearfix">
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href=""><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div>
					</div>

					<div class="col-md-6 widget">
						<h3 class="widget-title">关于我们</h3>
						<div class="widget-body">
							<p>我们是......</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">
					
					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="simplenav">
								<a href="#">Home</a> | 
								<a href="about.html">About</a> |
								<a href="sidebar-right.html">Sidebar</a> |
								<a href="contact.html">Contact</a> |
								<b><a href="signup.html">Sign up</a></b>
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2014, Your name. Designed by <a href="http://gettemplate.com/" rel="designer">Keen Cloud</a> 
							</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
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