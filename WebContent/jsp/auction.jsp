 <%@page import="com.kc.inspiration.biz.impl.UserBizImpl"%>
<%@page import="com.kc.inspiration.biz.IUserBiz"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kc.inspiration.vo.Nwork"%>
<%@page import="com.kc.inspiration.po.Auction"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.biz.impl.NworkBizImpl"%>
<%@page import="com.kc.inspiration.biz.INworkBiz"%>
<%@page import="com.kc.inspiration.biz.impl.AuctionBizImpl"%>
<%@page import="com.kc.inspiration.biz.IAuctionBiz"%>
<%@page import="com.kc.inspiration.po.User"%>
<%@page import="com.kc.inspiration.po.Work"%>
<%@page import="com.kc.inspiration.biz.impl.ResourceBizImpl"%>
<%@page import="com.kc.inspiration.biz.IResourceBiz"%>
<%@page import="com.kc.inspiration.po.Resource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>Inspiration-我的买卖</title>

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
	<script>
		function findRecoder(wid){
			window.location="/ins/AuctionRecoderServlet?wid="+wid;
		}
	</script>
</head>

<body>
	<%User user = (User)session.getAttribute("user");
	IUserBiz userBiz = new UserBizImpl();
	IAuctionBiz auctionBiz = new AuctionBizImpl();
	INworkBiz nworkBiz = new NworkBizImpl();
	//未拍卖的
	List<Auction> lstAuction1 = auctionBiz.findAuctionByUidStatus(user.getUid(), 1);
	//正在拍卖的
	List<Auction> lstAuction2 = auctionBiz.findAuctionByUidStatus(user.getUid(), 2);
	//拍卖成功的
	List<Auction> lstAuction4 = auctionBiz.findAuctionByUidStatus(user.getUid(), 4);
	//拍卖失败的
	List<Auction> lstAuction5 = auctionBiz.findAuctionByUidStatus(user.getUid(), 5);
	List<Nwork> lstWork1 = new ArrayList<Nwork>();
	List<Nwork> lstWork2 = new ArrayList<Nwork>();
	List<Nwork> lstWork4 = new ArrayList<Nwork>();
	List<Nwork> lstWork5 = new ArrayList<Nwork>();
	for(int i=0;i<lstAuction1.size();i++){
		Nwork work = new Nwork();
		work = nworkBiz.findById(lstAuction1.get(i).getWid());
		lstWork1.add(work);
	}
	for(int i=0;i<lstAuction2.size();i++){
		Nwork work = new Nwork();
		work = nworkBiz.findById(lstAuction2.get(i).getWid());
		lstWork2.add(work);
	}
	for(int i=0;i<lstAuction4.size();i++){
		Nwork work = new Nwork();
		work = nworkBiz.findById(lstAuction4.get(i).getWid());
		lstWork4.add(work);
	}
	for(int i=0;i<lstAuction5.size();i++){
		Nwork work = new Nwork();
		work = nworkBiz.findById(lstAuction5.get(i).getWid());
		lstWork5.add(work);
	}
	//System.out.println("wangrk"+lstWork.size());
	//System.out.println("wangrk"+lstWorked.size());
	List<Nwork> lstWork_1 = new ArrayList<Nwork>();
	List<Nwork> lstWork_2 = new ArrayList<Nwork>();
	List<Nwork> lstWork_4 = new ArrayList<Nwork>();
	List<Nwork> lstWork_5 = new ArrayList<Nwork>();
		if(lstWork1.size() > 3){
			for(int i=0;i<3;i++){
				lstWork_1.add(lstWork1.get(i));
			}
		}
		else{
			for(int i=0;i<lstWork1.size();i++){
				lstWork_1.add(lstWork1.get(i));
			}
		}
		if(lstWork2.size() > 3){
			for(int i=0;i<3;i++){
				lstWork_2.add(lstWork2.get(i));
			}
		}
		else{
			for(int i=0;i<lstWork2.size();i++){
				lstWork_2.add(lstWork2.get(i));
			}
		}
		if(lstWork4.size() > 3){
			for(int i=0;i<3;i++){
				lstWork_4.add(lstWork4.get(i));
			}
		}
		else{
			for(int i=0;i<lstWork4.size();i++){
				lstWork_4.add(lstWork4.get(i));
			}
		}
		if(lstWork5.size() > 3){
			for(int i=0;i<3;i++){
				lstWork_5.add(lstWork5.get(i));
			}
		}
		else{
			for(int i=0;i<lstWork5.size();i++){
				lstWork_5.add(lstWork5.get(i));
			}
		}
	request.setAttribute("lstWork_1", lstWork_1);
	request.setAttribute("lstWork_2", lstWork_2);
	request.setAttribute("lstWork_4", lstWork_4);
	request.setAttribute("lstWork_5", lstWork_5);
	if(lstWork_1.size()==0){
		out.print("<script>alert('没有未拍卖的物品！');</script>");
	}
	if(lstWork_2.size()==0){
		out.print("<script>alert('没有正在拍卖的物品！');</script>");
	}
	if(lstWork_4.size()==0){
		out.print("<script>alert('没有拍卖成功的物品！');</script>");
	}
	if(lstWork_5.size()==0){
		out.print("<script>alert('没有拍卖失败的物品！');</script>");
	}
	%>
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
	<div class="container" >

		<ol class="breadcrumb">
			<li><a href="/ins/jsp/home.jsp">我的INS</a></li>
			<li class="active">我的交易</li>
		</ol>

		<div class="row" style="backgroud-color:#CCFFFF">
			<!-- Article main content -->

			<div class="col-sm-9" >
				<header class="page-header">
					<h2 class="page-title"><strong>我的交易</strong></h2>
				</header>
			<div class="row">
			<div class="col-sm-12" style="margin:10px"><h4>未拍卖的商品</h4><hr>
			<div class="row">
			<c:forEach items="${requestScope.lstWork_1 }" var="nwork">
			<div class="col-sm-4">
			<article >
			
				<h4><a href="javascript:findRecoder(${nwork.wid })">${nwork.workname}</a></h4>
				<label>${nwork.publishtime}</label>
				<p><img src="/ins/upload/images/works/${nwork.path}" alt="" class="pull-left" height="200" width="400" ></p>
				<p>${nwork.workdescription}</p>
					
			</article>
			</div>
			</c:forEach>
			
			<div class="col-sm-3 col-sm-offset-9"><button type="button" class="btn btn-primary btn-xs">查看更多...</button></div>
			</div>
			</div>
			<div class="col-sm-12"><h4>正在拍卖的商品</h4><hr>
			<div class="row">
			<c:forEach items="${requestScope.lstWork_2 }" var="nwork">
			<div class="col-sm-4">
			<article >
			<% %>
				<h4><a href="javascript:findRecoder(${nwork.wid })">${nwork.workname}</a></h4>
				<p><img src="/ins/upload/images/works/${nwork.path}" alt="200X400" class="pull-left" height="200" width="400" ></p>
				<p>${nwork.workdescription}</p>
					
			</article>
			</div>
			</c:forEach>
			
			<div class="col-sm-3 col-sm-offset-9"><button type="button" class="btn btn-primary btn-xs">查看更多...</button></div>
			</div>
			</div>
			
			<div class="col-sm-12" style="margin:10px"><h4>拍卖成功的商品</h4><hr>
			<div class="row">
			<c:forEach items="${requestScope.lstWork_4 }" var="nwork">
			<div class="col-sm-4">
			<article >
			
				<h4><a href="javascript:findRecoder(${nwork.wid })">${nwork.workname}</a></h4>
				<label>${nwork.publishtime}</label>
				<p><img src="/ins/upload/images/works/${nwork.path}" alt="" class="pull-left" height="200" width="400" ></p>
				<p>${nwork.workdescription}</p>
					
			</article>
			</div>
			</c:forEach>
			
			<div class="col-sm-3 col-sm-offset-9"><button type="button" class="btn btn-primary btn-xs">查看更多...</button></div>
			</div>
			</div>
			
			
			<div class="col-sm-12" style="margin:10px"><h4>拍卖失败的商品</h4><hr>
			<div class="row">
			<c:forEach items="${requestScope.lstWork_5 }" var="nwork">
			<div class="col-sm-4">
			<article >
			
				<h4><a href="javascript:findRecoder(${nwork.wid })">${nwork.workname}</a></h4>
				<label>${nwork.publishtime}</label>
				<p><img src="/ins/upload/images/works/${nwork.path}" alt="" class="pull-left" height="200" width="400" ></p>
				<p>${nwork.workdescription}</p>
					
			</article>
			</div>
			</c:forEach>
			
			<div class="col-sm-3 col-sm-offset-9"><button type="button" class="btn btn-primary btn-xs">查看更多...</button></div>
			</div>
			</div>
</div>
</div>
			<!-- /Article -->
			<div class="col-sm-1"></div>
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
								Copyright &copy; 2014, Inspiration. Designed by <a href="#" rel="designer">Keen Cloud</a> 
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