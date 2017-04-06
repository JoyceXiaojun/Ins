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

<title>Inspiration-我的个人主页</title>

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
	
</head>

<body>
	<c:if test="${empty sessionScope.user }">
		<script>
			alert("亲！对不起，请先登录，登录后才能查看自己的首页!");
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
				<ul class="nav navbar-nav pull-left" style="margin-left:80px">
				
					<li><a href="/ins/jsp/home.jsp">首页</a></li>
					<li><a href="/ins/jsp/profile.jsp">个人主页</a></li>
					<li class="dropdown" class="active"><a href="/ins/jsp/information.jsp"
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
						<form class="navbar-form navbar-left" action="/ins/jsp/addfollow.jsp">

							<input name="search" type="text" class="form-control" placeholder="找人、作品、图片...">

							<a class="btn" type="submit" href="/ins/jsp/search.jsp" style="margin-top:0;"><i
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
	User user = (User) session.getAttribute("user");
	if(user==null){
		%>
		<script type="text/javascript">
		alert("亲！请先登录！");
		location = "/ins/jsp/signin.jsp";
		</script>
		<%
	}
	IUserBiz userBiz = new UserBizImpl();
	%>
	<!-- 中部 -->
	<div class="container">
		<div class="row">
			<!-- Sidebar -->
			<aside class="col-sm-2 sidebar sidebar-right">
			<div class="list-group">
				<a class="list-group-item" href="/ins/jsp/information.jsp">
						<strong>${sessionScope.user.username }</strong>
				</a> 
				<div class="list-group-item" style="padding:0;">
					<img alt="头像" src="/ins/upload/images/photo/${sessionScope.user.photo }" >
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
					<span class="badge" >0</span><i class="fa icon-comments fa-lg fa-fw"></i>我的消息
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

			<!-- main content -->
			<div class="col-sm-8 maincontent">
				<header class="page-header">
				<h3 class="page-title">最新发布</h3>
				</header>
				<%	
					IWorkUserBiz workUserBiz = new WorkUserBizImpl();
					List<WorkUser> wulst = workUserBiz.findByUid(user.getUid());
					if (wulst == null) {
				%>
				<P>没有新作品！</P>
				<%
					} else {
						for (WorkUser wu : wulst) {
							IResourceBiz resourceBiz =new ResourceBizImpl();
							Resource resource=resourceBiz.findById(wu.getWork().getRid());
				%>
				<div class="row">
					<div>
						<div class="col-sm-12">
						<img src="/ins/upload/images/photo/<%=wu.getUser().getPhoto() %>" alt="60X60" class="img-rounded" width="60" height="60" style="float:left">
						<h4><a href="#">&nbsp;&nbsp;@<%=wu.getUser().getUsername()%></a>&nbsp;&nbsp;发布了一件新作品
						&nbsp;&nbsp;<a href="/ins/jsp/work.jsp?wid=<%=wu.getWork().getWid() %>"><%=wu.getWork().getWorkname()%></a>
						&nbsp;&nbsp;
						<label>#<%=wu.getWork().getCatagory() %>#</label></h4>
						<h5>&nbsp;&nbsp;<%=wu.getWork().getPublishtime() %></h5>
						<p><img src="/ins/upload/images/works/<%=resource.getPath() %>" alt="250" class="img-rounded pull-left" width="250">
						<%=wu.getWork().getWorkdescription() %></p>
						</div>
						<div class="col-sm-12">
							<p class="text-right">
							<label id="category">类别：<%=wu.getWork().getCatagory() %></label>
							<label id="praisecount<%=wu.getWork().getWid() %>">点赞个数：<%=wu.getWork().getPraisecount() %></label>
							<label id="collectcount<%=wu.getWork().getWid() %>">收藏次数：<%=wu.getWork().getCollectcount() %></label>
							<label id="replycount">回复条数：<%=wu.getWork().getReplycount() %></label></p>
						</div>
						<div class="col-sm-12">
							<a class="btn btn-sm pull-right" href="#" onclick="praise(<%=wu.getWork().getWid()%>)"><i class="icon-thumbs-up"></i>赞</a>
							<a class="btn btn-sm pull-right" href="#" onclick="collect(<%=wu.getWork().getWid()%>)"><i class="icon-heart"></i>收藏</a>  
						    <a class="btn btn-sm pull-right" href="/ins/jsp/work.jsp?wid=<%=wu.getWork().getWid() %>"><i class="icon-comment"></i>评论</a>
						    <hr>
						</div>
					<hr>	
					</div>	
				</div>
					<%
						}
						}
					%>
				</div>
				<!-- / main content -->
				<!-- Sidebar -->
				<aside class="col-sm-2 sidebar sidebar-right">
				<h4>
					<i class="icon-heart icon-large"></i>热门推荐
				</h4>
				<div class="widget">
					<%
					IWorkBiz workBiz=new WorkBizImpl();
					List<Work> wlst=workBiz.findHot(1, 4);
					IResourceBiz resourceBiz=new ResourceBizImpl();
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
							<p>我们是...</p>
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
								Copyright &copy; 2014,Inspiration. Designed by <a
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
					var responseLabel=document.getElementById("praisecount");
					var xmlDoc=xmlHttp.responseXML;
					var flag=xmlDoc.getElementsByTagName("flag")[0].childNodes[0].nodeValue;
					var responseText=xmlDoc.getElementsByTagName("responsetext")[0].childNodes[0].nodeValue;
					if(flag=="true"){	
						alert("亲！恭喜你点赞成功！"+responseText);
					}else if(flag=="false"){
						alert("亲！您已经对该作品点过赞了！"+responseText);
					}else{
						alert("error!");
					}
				}
			}
		}
		
		function collectResults(){
			if(xmlHttp.readyState==4){
				if(xmlHttp.status==200){
					//do something interesting here
					var responseLabel=document.getElementById("collectcount");
					var xmlDoc=xmlHttp.responseXML;
					var flag=xmlDoc.getElementsByTagName("flag")[0].childNodes[0].nodeValue;
					var responseText=xmlDoc.getElementsByTagName("responsetext")[0].childNodes[0].nodeValue;
					if(flag=="true"){
						alert("亲！恭喜你收藏成功！"+responseText);
					}else{
						alert("亲！您已经收藏了该作品！"+responseText);
					}
				}
			}
		}
	</script>
</body>
</html>