<%@page import="com.kc.inspiration.biz.impl.UserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IUserBiz"%>
<%@page import="com.kc.inspiration.biz.impl.WorkBizImpl"%>
<%@page import="com.kc.inspiration.biz.IWorkBiz"%>
<%@page import="com.kc.inspiration.po.Comment"%>
<%@page import="com.kc.inspiration.po.User"%>
<%@page import="com.kc.inspiration.po.Work"%>
<%@page import="com.kc.inspiration.po.Resource"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.dao.impl.CommentDaoImpl"%>
<%@page import="com.kc.inspiration.dao.ICommentDao"%>
<%@page import="com.kc.inspiration.dao.impl.UserDaoImpl"%>
<%@page import="com.kc.inspiration.dao.IUserDao"%>
<%@page import="com.kc.inspiration.dao.impl.ResourceDaoImpl"%>
<%@page import="com.kc.inspiration.dao.IResourceDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">
<title>Inspiration-作品</title>
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
	function praise(wid){
		var url="/ins/PraiseServlet?wid="+wid;
		createXMLHttpRequest();
		xmlHttp.open("GET", url,true);
		xmlHttp.onreadystatechange=praiseResults;
		xmlHttp.send(null);
	}
	
	function collect(wid){
		var url="/ins/CollectServlet?wid="+wid;
		createXMLHttpRequest();
		xmlHttp.open("GET", url,true);
		xmlHttp.onreadystatechange=collectResults;
		xmlHttp.send(null);
	}
	
	function createXMLHttpRequest(){
		if(window.ActiveXObject){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}
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
					responseLabel.innerHTML= responseText+"/已点赞";
				}else if(flag=="false"){
					alert("亲！您已经对该作品点过赞了！"+responseText);
					responseLabel.innerHTML=responseText+"/已点赞";
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
					responseLabel.innerHTML=responseText+"/已收藏";
				}else if(flag=="false"){
					alert("亲！您已经收藏了该作品！"+responseText);
					responseLabel.innerHTML=responseText+"/已收藏";
				}else{
					alert("error!");
				}
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
				<a class="navbar-brand" href="/ins/jsp/index.jsp"><img
					src="/ins/images/logo.png" alt="Progressus HTML5 template"></a>
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
							<li><a href="/ins/jsp/message.jsp ">私信</a></li>
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
			<li class="active">作品</li>
		</ol>

		<div class="row">
			<!-- main content -->
			<%
				//获取url路径所带参数
				int wid=0;
				if(request.getParameter("wid")!=null&&!request.getParameter("wid").equals("")){
					wid=Integer.parseInt(String.valueOf(request.getParameter("wid")));
					System.out.println("[work.jsp]: >wid="+wid);
				}else{
					%>
					<script type="text/javascript">location="/ins/jsp/404.html";</script>
					<%
				}
			
				IWorkBiz workBiz=new WorkBizImpl();
				Work work =null;
				work= workBiz.findById(wid); 
				IUserBiz userBiz=new UserBizImpl();
				User author=userBiz.findById(work.getUid());
				IResourceDao resourceDao = new ResourceDaoImpl();
				Resource resource=null;
				if(work!=null){
					int rid = work.getRid();
					resource = resourceDao.selectById(rid);
					if(resource==null){
						System.out.println("找不到资源");
						%>
						<script type="text/javascript">location="/ins/jsp/404.html";</script>
						<%
					}			  
					//访问量加1
					work.setVisitcount(work.getVisitcount()+1);
					workBiz.modify(work);
				}
			%>
			<div class="col-md-9">
				<div class="row">
					<h3><%=work.getWorkname()%><small><%=work.getPublishtime()%></small>
					</h3>
				</div>
				<div class="row">
				<!-- 作品图片 -->
				<div>
					<img alt="作品图片" src="/ins/upload/images/works/<%=resource.getPath()%>">
				</div>
				<hr>
				<label>作者：<a href="/ins/jsp/following.jsp?uid=<%=author.getUid() %>"><%=author.getUsername() %></a></label>
				<h3>作品描述</h3>
				<p><%=work.getWorkdescription()%></p>
				</div>
				<!-- 社会化分享插件 -->
				<div class="jiathis_style_32x32 row">
					<a class="jiathis_button_qzone"></a>
					<a class="jiathis_button_tsina"></a>
					<a class="jiathis_button_tqq"></a>
					<a class="jiathis_button_weixin"></a>
					<a class="jiathis_button_renren"></a>
					<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
					<a class="jiathis_counter_style"></a>
				</div>
				<div>
				<p class="text-right">
					<label id="category">类别：<%=work.getCatagory()%></label>
					<label id="visitcount">访问量：<%=work.getVisitcount() %></label>
					<label id="praisecount">点赞个数：<%=work.getPraisecount() %></label> 
					<label id="collectcount">收藏次数：<%=work.getCollectcount() %></label>
					<label id="replycount">回复条数：<%=workBiz.getReplyCountById(work.getWid()) %></label>
				</p>
				</div>
				<div>
					<div>
						<%
							User user = (User) session.getAttribute("user");
						%>
						<button type="button" class="btn"
							onclick="praise(<%=work.getWid()%>)">点赞</button>

						<button type="button" class="btn"
							onclick="collect(<%=work.getWid()%>)">收藏</button>

						<button type="button" class="btn" onclick="showReply('reply1')">评论</button>
					</div>
					
					<br>
					<div id="reply1" name="reply" style="display: none;">

						<form name="replyForm" method="post" action="/ins/CommentServlet">
						<!-- 创建一个隐藏域对象存放主信息的ID编号 -->
							<input type="hidden" id="replycid" name="replycid" value="0" />
							<input type="hidden" id="userid" name="userid" value="<%=user.getUid()%>" /> 
							<input type="hidden" id="workid" name="workid" value="<%=work.getWid()%>" />
							<div class="form-group">
							<textarea class="form-control" name="content" id="content" rows="5"></textarea>
							</div>
							<div class="form-group">
							<input type="submit" class="btn" name="submit2" id="submit2" value="回复"> &nbsp; 
							<input type="button" class="btn" name="button" id="button" onclick="javascript:hiddenReply('reply1');" value="关闭">
							</div>
						</form>
					</div>
				</div>

				<div>
					<h3>评论</h3>
					<%
						ICommentDao commentDao = new CommentDaoImpl();
						IUserDao userDao = new UserDaoImpl();
						List<Comment> lstComments = commentDao.selectByReplyId(0,work.getWid());
					%>
					<%
						if (lstComments == null) {
					%>
					<p>没有评论</p>
					<%
						} else {
							for (Comment comment : lstComments) {
								int uid0 = comment.getUid();
								User users0 = userDao.selectById(uid0);
					%>
					<table width="50%" border="0">
						<tr>
							<td height="30"><%=users0.getUsername()%>&nbsp; <%=comment.getTime()%></td>
							<td width="14%" align="center" valign="middle"><a
								href="javascript:showReply('reply<%=comment.getCid()%>');">回复</a></td>
						</tr>
						<tr>
							<td height="30" colspan="2" bgcolor="#FFFFCC"><%=comment.getContent()%></td>
						</tr>
						<!-- 信息回复部分 -->
						<tr id="reply<%=comment.getCid()%>" name="reply"
							style="display: none;">
							<td height="30" colspan="2">

								<form name="replyForm" method="post"
									action="/ins/CommentServlet">
									<!-- 创建一个隐藏域对象存放主信息的ID编号 -->
									<input type="hidden" id="replycid" name="replycid"
										value="<%=comment.getCid()%>" /> <input type="hidden"
										id="userid" name="userid" value="<%=user.getUid()%>" /> <input
										type="hidden" id="workid" name="workid"
										value="<%=work.getWid()%>" />
									<table width="100%" border="0">
										<tr>
											<td height="30"><textarea name="content" id="content"
													cols="65" rows="5"></textarea></td>
										</tr>
										<tr>
											<td height="30"><input type="submit" name="submit2"
												id="submit2" value="回复"> &nbsp; <input type="button"
												name="button" id="button"
												onclick="javascript:hiddenReply('reply<%=comment.getCid()%>');"
												value="关闭"></td>
										</tr>
									</table>
								</form>

							</td>
						</tr>
						<%
							List<Comment> lstReply = commentDao.selectByReplyId(comment
											.getCid(),work.getWid());
									for (Comment c : lstReply) {
										int uid = c.getUid();
										User users = userDao.selectById(uid);
						%>
						<tr>
							<td height="30" colspan="2" bgcolor="#DAFEE8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<%=users.getUsername()%> 回复&nbsp;<%=users0.getUsername()%> <%=c.getTime()%>&nbsp;
								&nbsp;&nbsp;&nbsp;<%=c.getContent()%></td>
						</tr>
						<%
							}
						%>
					</table>
					<p>&nbsp;</p>
					<%
						}
					}
					%>
				</div>
			</div>
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
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
</body>
</html>