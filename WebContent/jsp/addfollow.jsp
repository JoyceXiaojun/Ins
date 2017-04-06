<%@page import="com.kc.inspiration.biz.impl.UserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IUserBiz"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.po.User"%>
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

<title>Inspiration-添加关注</title>

<link rel="shortcut icon" href="/ins/images/gt_favicon.png">

<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link rel="stylesheet" href="/ins/css/bootstrap.min.css">
<link rel="stylesheet" href="/ins/css/font-awesome.min.css">

<!-- Custom styles for our template -->
<link rel="stylesheet" href="/ins/css/bootstrap-theme.css" media="screen">
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
				<ul class="nav navbar-nav pull-left" style="margin-left: 80px">
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
			<li class="active">添加</li>
		</ol>

		<div class="row">
			<header class="page-header">
			<h2 class="page-title">
				<strong>添加关注</strong>
			</h2>
			</header>
			<!-- main content -->
			<div class="col-md-10">
			<div class="row">
				<div class="col-lg-6">
					<div class="input-group">
			    		<input type="text" id="text" class="form-control" placeholder="请输入用户名..." style="width:60%;">
			 			<span class="input-group-btn"> 		
			    		<button type="button" class="btn btn-default" onclick="javascript:search(text.value);">查找</button>
						</span>
					</div>
				</div>
			</div>
			<br>
			    <div class="row" id="result">
			    </div>
			    
			    <h2><strong>热门用户</strong></h2>
				<div class="row" id="all">
				<%
				User user =(User)session.getAttribute("user");
				if(user==null){
					%>
					<script>location = "/ins/jsp/404.html";</script>
					<%
				}
				IUserBiz userBiz=new UserBizImpl();
				System.out.println("Debug: >"+userBiz.toString());
				List<User> ulst=userBiz.findAllNotFollow(user.getUid());
				if(ulst==null||ulst.size()<=0){
					%>
					<strong>关注了所有用户......</strong>
					<%
				}
				else{
					int i=0;
					for(User notfollow:ulst){
						%>
						<div class="col-md-4">
						<div class="thumbnail item">
						<img alt="头像" src="/ins/upload/images/photo/<%=notfollow.getPhoto() %>">
						<div class="caption">
							<h3><%=notfollow.getUsername() %></h3>
							<p><label>个性签名：</label><strong><%=notfollow.getMark() %></strong></p>
							<p>
								<a class="btn btn-primary btn-xs" href="/ins/jsp/following.jsp?uid=<%=notfollow.getUid() %>">查看详细</a> 
								<button class="btn btn-xs" onclick="addFollow(<%=notfollow.getUid() %>)">添加关注</button>
							</p>
						</div>
						</div>
						</div>
						<%	
						i++;
						if(i>=3)
							break;
					}
				}
				%>
				</div>
				<div class="row">
					<a class="btn btn-primary" href="/ins/jsp/search.jsp" style="float: right">查看更多</a>
				</div>
			</div>
	
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
					<span class="badge" ><%=userBiz.getCollectCount(user.getUid()) %></span><i class="fa fa-star fa-lg fa-fw"></i>个人收藏
				</a>
				<a class="list-group-item" href="/ins/jsp/collect.jsp">
					<span class="badge" >0</span><i class="icon-comments fa-lg fa-fw"></i>我的消息
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
	<script language="javascript">
	function showReply(id){
		// 获取页面中所有的消息回复表单行对象的集合
		var lstTr = document.getElementsByName("reply");
		// 使用循环遍历每个对象将其显示属性设置为none
		for(var i=0; i<lstTr.length; i++){
			lstTr[i].style.display = "none";
		}
		// 将需要显示的回复表单行对象进行显示
		document.getElementById(id).style.display = "block";
	}
	function hiddenReply(id){
		document.getElementById(id).style.display = "none";
	}
	
	var xmlHttp;
	
	function createXMLHttpRequest(){
		if(window.ActiveXObject){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}
	}
	
	function search(str){
		var url="/ins/SearchUserServlet?str="+str;
		createXMLHttpRequest();
		xmlHttp.open("GET", url,true);
		xmlHttp.onreadystatechange=searchResults;
		xmlHttp.send(null);
	}
	
	function searchResults(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				//
				var xmlDoc=xmlHttp.responseXML;
				var uid=xmlDoc.getElementsByTagName("uid")[0].childNodes[0].nodeValue;
				var photo = xmlDoc.getElementsByTagName("photo")[0].childNodes[0].nodeValue;
                var uname = xmlDoc.getElementsByTagName("username")[0].childNodes[0].nodeValue;
                var mark = xmlDoc.getElementsByTagName("mark")[0].childNodes[0].nodeValue;
                var uid = xmlDoc.getElementsByTagName("uid")[0].childNodes[0].nodeValue;
                var followArea = document.getElementById("result");
                //
		        followArea.innerHTML = "<div class='col-md-4'>"+
				"<div class='thumbnail'>"+
				"<img alt='头像' src='/ins/upload/images/photo/"+photo+"'>"+
				"<div class='caption'>"+
					"<h3>"+uname+"</h3>"+
					"<p>"+mark+"<p>"+
						"<a class='btn btn-primary btn-xs' href='/ins/jsp/following.jsp?uid="+uid+"'>查看详细</a>"+ 
						"<button class='btn btn-xs' onclick='javascript:addFollow("+uid+");'>添加关注</button>"+
					"</p>"+
				"</div>"+
				"</div>"+
				"</div>";
          }
		}
	}
	
	function addFollow(uid){
		var url="/ins/AddFollowServlet?uid="+uid;
		createXMLHttpRequest();
		xmlHttp.open("GET", url,true);
		xmlHttp.onreadystatechange=addResults;
		xmlHttp.send(null);
	}
	
	function addResults(){
		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
				var xmlDoc=xmlHttp.responseXML;
				var passed=xmlDoc.getElementsByTagName("passed")[0].childNodes[0].nodeValue;
				var followArea = document.getElementById("result");
				followArea.innerHTML = "<p><strong>"+passed+"</strong></p>";
				alert(passed);
			}
		}
	}
	
	function setFollow(username,mark){
		var followArea = document.getElementById("result");
		followArea.innerHTML = "<p>"+username+mark+"</p>"
	}
	function debug(uid){
		alert("debug");
	}
	function $(id){
		return document.getElementById(id);
	}
	function setHeight(){
		//获取row的高度
		var height=document.getElementById("all").offsetHeight;
		//获取所有div
		var itemlist=document.getElementsByClassName("item");
		for(var i=0;i<itemlist.length;i++){
			itemlist[i].style.height=height+"px";
		}
	}
	window.onload=function(){
		setHeight();
	};
	</script>
</body>
</html>