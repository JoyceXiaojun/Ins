<%@page import="com.kc.inspiration.biz.impl.UserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IUserBiz"%>
<%@page import="com.kc.inspiration.biz.impl.ResourceBizImpl"%>
<%@page import="com.kc.inspiration.biz.IResourceBiz"%>
<%@page import="com.kc.inspiration.po.Resource"%>
<%@page import="com.kc.inspiration.po.Work"%>
<%@page import="com.kc.inspiration.biz.impl.WorkBizImpl"%>
<%@page import="com.kc.inspiration.biz.IWorkBiz"%>
<%@page import="com.kc.inspiration.vo.WorkUser"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.po.User"%>
<%@page import="com.kc.inspiration.biz.impl.WorkUserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IWorkUserBiz"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet" href="/ins/css/templatemo_style.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="/ins/js/html5shiv.js"></script>
	<script src="/ins/js/respond.min.js"></script>
	<![endif]-->
		<script type="text/javascript">
	var xmlHttp;
	function createXMLHttpRequest(){
		if(window.ActiveXObject){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}
	}
	function collect(wid){
		
		var url="/ins/CollectServlet?wid="+wid;
		createXMLHttpRequest();
		xmlHttp.open("GET", url,true);
		xmlHttp.onreadystatechange=collectResults;
		xmlHttp.send(null);
	}
	function collectResults(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){	
				//do something interesting here
				var responseLabel=document.getElementById("collectcount");
				if(responseLabel.hasChildNodes()){
					responseLabel.removeChild(responseLabel.childNodes[0]);
				}
				var responseText=document.createTextNode(xmlHttp.responseText);
				responseLabel.appendChild(responseText);
			}
		}
	}
	function praise(wid){
		var url="/ins/PraiseServlet?wid="+wid;
		createXMLHttpRequest();
		xmlHttp.open("GET", url,true);
		xmlHttp.onreadystatechange=praiseResults;
		xmlHttp.send(null);
	}
	
	function praiseResults(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				//do something interesting here
				var responseLabel=document.getElementById("praisecount");
				if(responseLabel.hasChildNodes()){
					responseLabel.removeChild(responseLabel.childNodes[0]);
				}
				var responseText=document.createTextNode(xmlHttp.responseText);
				responseLabel.appendChild(responseText);
			}
		}
	}
	</script>
</head>

<body>
	<c:if test="${empty sessionScope.user }">
		<script>
			alert("亲！请先登录！");
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
				<a class="navbar-brand" href="#"><img src="/ins/images/logo.png"
					alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-left" style="margin-left:80px">
					<li><a href="/ins/jsp/home.jsp">首页</a></li>
					<li><a href="/ins/jsp/profile.jsp">个人主页</a></li>
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

							<input type="text" class="form-control" placeholder="找人、作品、图片...">

							<a class="btn" type="submit" style="margin-top:0;"><i
								class="icon-search" href="/ins/jsp/search.jsp"></i></a>
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
	IUserBiz userBiz=new UserBizImpl();
	User user = userBiz.findById(Integer.parseInt(request.getParameter("uid")));%>
	<!-- 中部 -->
	<div class="container">
		<div class="row">
			<!-- Sidebar -->
			<aside class="col-sm-2 sidebar sidebar-right">
			<p class="text-center">
			<img alt="100X100" src="/ins/upload/images/photo/<%=user.getPhoto() %>" class="img-circle" width="100" height="100">
			</p>
			<p class="text-center"><%=user.getUsername() %></p>
			<div class="menu-wrapper">
				<ul class="menu" style="display: block;">
					<li><a class="homebutton" href="/ins/jsp/index.jsp"
						style="text-decoration: none"><i class="icon-home icon-large"></i>
							&nbsp;&nbsp;&nbsp;首&nbsp;&nbsp;&nbsp;页</a></li>
					<li><a class="homebutton" href="/ins/jsp/friendsprofile.jsp?uid=<%=user.getUid() %>"
						style="text-decoration: none"><i
							class="icon-comments icon-large"></i>
							&nbsp;&nbsp;&nbsp;资&nbsp;&nbsp;&nbsp;料</a></li>

				</ul>

			</div>
			</aside>
			<!-- /Sidebar -->

			<!-- main content -->
			<div class="col-sm-8 maincontent">
				<header class="page-header">
				<h3 class="page-title"><%=user.getUsername() %>的最新状态</h3>
				</header>
				<%	
					IWorkBiz workBiz = new WorkBizImpl();
					List<Work> wlst = workBiz.findAll(user.getUid());
					IResourceBiz resourceBiz=new ResourceBizImpl();
					if (wlst == null) {
				%>
				<P>没有新作品！</P>
				<%
					} else {
						for (Work work : wlst) {
							Resource res=resourceBiz.findById(work.getRid());
				%>
				<div class="row">
					<div>
						<div class="col-sm-12">
						<img src="/ins/upload/images/photo/<%=user.getPhoto() %>" alt="60X60" class="img-rounded" width="60" height="60" style="float:left">
						<h4>&nbsp;&nbsp;<a href="/ins/jsp/work.jsp?<%=work.getWid() %>"><%=work.getWorkname()%></a>
						&nbsp;&nbsp;<a href="#">@<%=user.getUsername()%></a>&nbsp;&nbsp;#<%=work.getCatagory() %>#</h4>
						<h5>&nbsp;&nbsp;<%=work.getPublishtime() %></h5>
						<p><img src="/ins/upload/images/works/<%=res.getPath() %>" alt="250" class="img-rounded pull-left" width="250">
						<%=work.getWorkdescription()%></p>
						</div>
						<div class="col-sm-12">
							<p class="text-right">
							<label id="category">类别：<%=work.getCatagory() %></label>
							<label id="praisecount">点赞个数：<%=work.getPraisecount() %></label>
							<label id="collectcount">收藏次数：<%=work.getCollectcount() %></label>
							<label id="replycount">回复条数：<%=work.getReplycount() %></label></p>
						</div>
						<div class="col-sm-12">
							<a class="btn btn-sm pull-right" href="#" onclick="praise(<%=work.getWid()%>)"><i class="icon-thumbs-up"></i>赞</a>
							<a class="btn btn-sm pull-right" href="#" onclick="collect(<%=work.getWid()%>)"><i class="icon-heart"></i>收藏</a>  
						    <a class="btn btn-sm pull-right" href="/ins/jsp/work.jsp?wid=<%=work.getWid() %>"><i class="icon-comment"></i>评论</a>
						    <hr>
						</div>
						
					</div>
					</div>

					<%
						}
					}
					%>
				</div>
				<!-- /Article -->
				<!-- Sidebar -->
				<aside class="col-sm-2 sidebar sidebar-right">

				<h4>
					<i class="icon-heart icon-large"></i>热门推荐
				</h4>
				<div class="widget">
					<%
					wlst=workBiz.findHot(1, 4);
					if(wlst!=null){
						for(int i=0;i<4&&i<wlst.size();i++){
							Resource res=resourceBiz.findById(wlst.get(i).getRid());
							%>
							<div class="row">
								<div class="thumbnail">
								<img alt="作品图片" src="/ins/upload/images/works/<%=res.getPath() %>" >
								<div class="caption">
									<h4><%=wlst.get(i).getWorkname() %></h4>
									<p><%=wlst.get(i).getWorkdescription() %></p>
									<p class="text-right">
									<a class="btn btn-primary btn-xs" href="/ins/jsp/work.jsp?wid=<%=wlst.get(i).getWid() %>" >浏览</a>
									</p>
								</div>
								</div>
							</div>
							<%
						}
					}
					%>	
				</div>

				</aside>
				<!-- /Sidebar -->

			</div>
		</div>
		<!-- /container -->
		<!-- 底部 -->

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
								<a href=""><i class="fa fa-twitter fa-2" style="color:"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a> <a href=""><i
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
								<a href="#">Home</a> | <a href="about.html">About</a> | <a
									href="sidebar-right.html">Sidebar</a> | <a href="contact.html">Contact</a>
								| <b><a href="signup.html">Sign up</a></b>
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2014. Designed by <a
									href="http://gettemplate.com/" rel="designer">keencloud</a>
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