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

<title>Inspiration-私信</title>

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
	<script>
		function changeDiv(id){
			var lstDiv = $("div[key='mydiv']");
			for(var i=0;i<lstDiv.size();i++){
				if(lstDiv[i].style.display!="none"){
					lstDiv[i].style.display="none";
				}
			}
			document.getElementById(id).style.display="block";
		}
		function Rid(rid){
			window.location="/ins/AuctionRecoderServlet?rid="+rid;
		}
	</script>
		<script type="text/javascript">
var xmlHttp;

function createXMLHttpRequest(){
	if(window.XMLHttpRequest){
		XMLHttpReq = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){
		try{
			XMLHttpReq = new ActiveXObject("Msxm12.XMLHttp");
		}
		catch(e){
			try{
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e){
				
			}
		}
	}
}

function processResponse(){
	if(XMLHttpReq.readyState==4){
		if(XMLHttpReq.status == 200){
			//alert(XMLHttpReq.responseText);
			//document.getElementById("chatArea").value = XMLHttpReq.chatList;
			var rid = XMLHttpReq.responseXML.getElementsByTagName("rid")[0].firstChild.data;
			var label = document.getElementById("chatArea"+rid);
			var msg = XMLHttpReq.responseXML.getElementsByTagName("msg")[0].firstChild.data;
			label.innerHTML=msg;
		}
		else{
			window.alert("您所请求的页面有异常！"+XMLHttpReq.status);
		}
	}
}
function processEmptyResponse(){
	if(XMLHttpReq.readyState==4){
		if(XMLHttpReq.status == 200){
			//alert(XMLHttpReq.responseText);
			//document.getElementById("chatArea").value = XMLHttpReq.chatList;
			var lstDiv = $("div[key='mydiv']");
			for(var i=0;i<lstDiv.size();i++){
				if(lstDiv[i].style.display!="none"){
					rid = lstDiv[i].id;
				}
			}
			for(var i=0;i<XMLHttpReq.responseXML.getElementsByTagName("rid").length;i++){
				if(XMLHttpReq.responseXML.getElementsByTagName("rid")[i].firstChild.data==rid){
					
					var msg=XMLHttpReq.responseXML.getElementsByTagName("msg")[i].firstChild.data;
				}
			}
			
			var label = document.getElementById("chatArea"+rid);
			label.innerHTML=msg;
		}
		else{
			window.alert("您所请求的页面有异常！"+XMLHttpReq.status);
		}
	}
}

function sendRequest(rid){
	var chatMsg = document.getElementById("chatMsg"+rid).value;
	//alert(chatMsg);
	createXMLHttpRequest();
	var url = "/ins/ChatServlet?chatMsg="+chatMsg+"&rid="+rid;
	
	XMLHttpReq.open("GET",url,true);
	//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	XMLHttpReq.onreadystatechange = processResponse;
	document.getElementById("chatMsg"+rid).value = "";
	XMLHttpReq.send(null);
	
}

function sendEmptyRequest(){
	//alert(chatMsg);
	
	createXMLHttpRequest();	
	
	var url = "/ins/SearchChatServlet";
	
	XMLHttpReq.open("GET",url,true);
	//XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	XMLHttpReq.onreadystatechange = processEmptyResponse;
	//input.value = "";
	XMLHttpReq.send(null);
	setTimeout("sendEmptyRequest()",800);
}
</script>
</head>

<body onload="sendEmptyRequest()">
	<c:if test="${empty sessionScope.user }">
		<script>
			alert("亲！请先登录！");
			location = "/ins/jsp/signin.jsp";
		</script>
	</c:if>
	<%User user = (User)session.getAttribute("user");
	IUserBiz userBiz = new UserBizImpl();
	List<User> lstFollow = userBiz.findAllFollow(user.getUid());
	request.setAttribute("lstFollow", lstFollow); %>
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

							<a class="btn btn-sm" type="submit" href="/ins/jsp/search.jsp" style="margin-top:0;"><i
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
	<div class="container" >
		<div class="row">
		
			<aside class="col-md-3 sidebar sidebar-left">
			<div class="list-group"  style="height:396px">
				<a href="#" class="list-group-item"><strong>好友列表</strong></a>
				
				<c:forEach items="${requestScope.lstFollow }" var="user">
				<a class="list-group-item" href="javascript:changeDiv(${user.uid });">
					<span class="badge">14</span><img src="/ins/upload/images/photo/${user.photo } " class="img-circle" width="50" height="50" >${user.username }
					</a>
				</c:forEach>
				
				<a class="list-group-item" ><strong>我的INS</strong></a>
			</div>

			</aside>
			<!-- /Sidebar -->
			
			<!-- main content -->
			<c:forEach items="${requestScope.lstFollow }" var="user">
			<div id="${user.uid }" key="mydiv" class="col-md-6" style="padding-top:36px;display:none;" >
			<div class="list-group">
				<a href="#" class="list-group-item active">聊天</a>
				<textarea name="textarea" id="chatArea${user.uid }" class="form-control" readonly="readonly" cols="" rows="14px"></textarea>
				<div class="list-group-item active">
		
				<form action="" role="form" class="form-inline">
					<div class="form-group" style="width:350px">
						<div class="input-group" style="width:350px">
							<input id="chatMsg${user.uid }" type="text" class="form-control"  style="widtn:350px;" placeholder="我想你了......">
						</div>
					</div>
				<button type="button" id="${user.uid}"name="sendbtn"class="btn btn-default" style="float:right;" onClick="sendRequest(${user.uid });this.isclick=1;">发送</button>
				</form>
				</div>
			</div>
			</div>
			</c:forEach>
			<!-- Sidebar -->
			
			<aside class="col-md-3 sidebar sidebar-right" style="float:right" id="sidebar">

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
	
	<script type="text/javascript">
	$(document).ready(function() {
		
		
	});
	</script>
</body>
</html>