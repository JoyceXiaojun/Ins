<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.po.Resource"%>
<%@page import="com.kc.inspiration.biz.impl.ResourceBizImpl"%>
<%@page import="com.kc.inspiration.dao.impl.ResourceDaoImpl"%>
<%@page import="com.kc.inspiration.biz.IResourceBiz"%>
<%@page import="com.kc.inspiration.biz.IWorkBiz"%>
<%@page import="com.kc.inspiration.biz.impl.WorkBizImpl"%>
<%@page import="com.kc.inspiration.po.Work"%>
<%@page import="com.kc.inspiration.po.Auction"%>
<%@page import="com.kc.inspiration.biz.impl.AuctionBizImpl"%>
<%@page import="com.kc.inspiration.biz.IAuctionBiz"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>Inspiration-我要竞拍</title>

	<link rel="shortcut icon" href="/ins/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="/ins/css/bootstrap.min.css">
	<link rel="stylesheet" href="/ins/css/font-awesome.min.css">
	<link rel="stylesheet" href="/ins/css/bootstrap-theme.css" media="screen" >
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
		case 100:
			out.print("<script>alert('对不起，您出的价格小于当前最高价！');</script>)");
			break;
		case 101:
			out.print("<script>alert('恭喜您出价成功！');</script>");
			break;
		case 102:
		case 103:
			out.print("<script>alert('很抱歉，您出价失败！');</script>");
			break;
		default:
			break;
		}
	%>
	</c:if>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="#"><img src="/ins/images/logo.png" alt="Progressus HTML5 template"></a>
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
						
							<a class="btn btn-sm" class="/ins/jsp/search" type="submit" style="margin-top:0;"><i class="icon-search"></i></a>
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
			<li><a href="/ins/jsp/home.jsp">首页</a></li>
			<li class="active">我要竞拍</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<div class="col-md-8">
				<header class="page-header">
					<h2 class="page-title">我要竞拍</h2>
				</header>
				<%
				IAuctionBiz auctionBiz=new AuctionBizImpl();
				Auction auction =auctionBiz.findById(Integer.parseInt(request.getParameter("aid")));
				IWorkBiz workBiz=new WorkBizImpl();
				Work work=workBiz.findById(auction.getWid());
				IResourceBiz resourceBiz = new ResourceBizImpl();
				Resource resource = resourceBiz.findById(work.getRid());
				%>
				<div class="row">
					<div class="col-md-12">
						<h3>作品详情</h3>
						<p><img src="/ins/upload/images/works/<%=resource.getPath() %>" alt="" class="img-rounded pull-center">
						</p>
						<hr>
						<h4>作品名称</h4>
						&nbsp;&nbsp;<a href="/ins/jsp/work?wid=<%=work.getWid() %>"><%=work.getWorkname() %></a>
						
						<h4>作品描述</h4>
						<p>&nbsp;&nbsp;&nbsp;&nbsp;<strong><%=work.getWorkdescription() %></strong></p>
					</div>
					<div class="col-md-12">
						<h3>底价</h3>
						<p><strong><%=auction.getBaseprice() %></strong></p>
						<h3>当前最高价</h3>
						   <p><strong><%=auction.getTopprice() %></strong></p>
						<h3>开始时间</h3>
						<p><strong><%=auction.getBegintime() %></strong></p>
						<h3>结束时间</h3>
						<p><strong><%=auction.getEndtime() %></strong></p>
						<h3>拍卖备注</h3>
						<p><%=auction.getMark()==null?"无":auction.getMark() %></p>
						<form action="/ins/BidServlet" role="form">
						<input type="hidden" name="aid" value="<%=auction.getAid() %>">
						<div class="form-group">
						<label>输入你的出价</label>
						<div class="input-group">
							<span class="input-group-addon">￥</span>
							<input type="text" name="price" class="form-control" placeholder="输入您的价钱，如999.99">
							<span class="input-group-addon">.00</span>
						</div>
						</div>
						<button type="submit" class="btn btn-primary">提交</button>
						<button type="reset" class="btn">取消</button>
						</form>
					</div>
					
				</div>
			</div>
			<!-- /Article -->
			
			<div class="col-md-1"></div>
			<!-- Sidebar -->
			<aside class="col-md-3 sidebar sidebar-right">
				<h4>
					<i class="icon-heart icon-large"></i>热门推荐
				</h4>
				<div class="widget">
					<%
					List<Work> wlst=workBiz.findHot(1, 4);
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
							<p>我们是来自南开大学的一群大学生...</p>				
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
								Copyright &copy; 2014, Your name. Designed by <a href="http://gettemplate.com/" rel="designer">gettemplate</a> 
							</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>
	</footer>	

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="/ins/js/headroom.min.js"></script>
	<script src="/ins/js/jQuery.headroom.min.js"></script>
	<script src="/ins/js/template.js"></script>

	<script src="/ins/js/jquery.min.js"></script>
	<script src="/ins/js/bootstrap.min.js"></script>
	<script src="/ins/js/headroom.min.js"></script>
	<script src="/ins/js/jQuery.headroom.min.js"></script>
	<script src="/ins/js/template.js"></script>
</body>
</html>