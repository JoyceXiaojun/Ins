<%@page import="com.kc.inspiration.po.Resource"%>
<%@page import="com.kc.inspiration.biz.impl.ResourceBizImpl"%>
<%@page import="com.kc.inspiration.biz.IResourceBiz"%>
<%@page import="com.kc.inspiration.po.Work"%>
<%@page import="java.util.List"%>
<%@page import="com.kc.inspiration.biz.impl.WorkBizImpl"%>
<%@page import="com.kc.inspiration.biz.IWorkBiz"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>Inspiration-快来注册吧！</title>

	<link rel="shortcut icon" href="/ins/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="/ins/css/bootstrap.min.css">
	<link rel="stylesheet" href="/ins/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="/ins/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="/ins/css/main.css">
	<link rel="stylesheet" href="/ins/css/templatemo_style.css">
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="/ins/js/html5shiv.js"></script>
	<script src="/ins/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="/ins/jsp/index.jsp"><img src="/ins/images/logo.png" alt="Progressus HTML5 template"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li >
						<form class="navbar-form navbar-left" >
							<input type="text" class="form-control" placeholder="找人、作品、图片...">
							<a class="btn btn-sm" type="submit" style="margin-top:0;"><i class="icon-search"></i>搜索</a>
						</form>
					</li>
					<li><a href="/ins/jsp/signin.jsp">登录</a></li>
					<li><a href="/ins/jsp/signup.jsp">注册</a></li>
				</ul>
			</div><!--/.nav-collapse -->
			<div class="navbar-collapse collapse">
			</div>
		</div>
	</div> 
	<!-- /.navbar -->
	<header id="head" class="secondary"></header>
	<!-- 获取p参数 -->
	<!-- 中部 -->
	<div class="container">
		<div class="row">
			<!-- main content -->
			<div class="col-sm-10">
				<header class="page-header">
					<h2 class="page-title" >热门作品</h2>
				</header>
				<%
					int p;
					if(request.getParameter("p")==null){
						p=1;
					}else{
						p=Integer.parseInt(String.valueOf(request.getParameter("p")));
						System.out.println("[praise.jsp]: >p="+p);
					}
					IWorkBiz workBiz=new WorkBizImpl();
					List<Work> wlst=workBiz.findHot(8*(p-1)+1, 8*p);
					//System.out.println("[visitor.jsp]: > p="+p);
					IResourceBiz resourceBiz=new ResourceBizImpl();
					if(wlst!=null){
						//System.out.println("[visitor.jsp]: > wlst.size()="+wlst.size());
						%>
						<div class="row" id="row1">
						<%
						for(int i=0;i<4 && i<wlst.size();i++){
							Resource res=resourceBiz.findById(wlst.get(i).getRid());
							%>
							<div class="col-sm-3">
								<div class="thumbnail item1">
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
						%>
						</div>
						<div class="row" id="row2">
						<%
						for(int i=4;i<8 && i<wlst.size();i++){
							Resource res=resourceBiz.findById(wlst.get(i).getRid());
							%>
							<div class="col-sm-3">
								<div class="thumbnail item2">
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
						%>
						</div>
						<%
					}
				%>	
				<!-- 按钮组，实现分页 -->
				<p class="text-center" style="font-size:24px;">
				<%
				for(int i=1;i<=6;i++){
				%>
					<a href="/ins/jsp/visitor.jsp?p=<%=i %>" class=""><%=i %></a>
				<% 	
				}
				%>
				</p>
			</div>
			<!-- /Article -->
			
			<!-- Sidebar -->
			<aside class="col-sm-2 sidebar sidebar-right">

				<div class="widget">
					<h4><i class="icon-heart icon-large" ></i>网站简介</h4>
					<ul class="list-unstyled list-spaces">
						<li><a href="">关于我们</a><br><span class="text-muted">我们是......</span></li>
						<li><a href="">关注我们</a><br>
							<span class="text-muted"><a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href=""><i class="fa fa-facebook fa-2"></i></a>
							</span></li>
						<li><a href="">联系我们</a><br><span class="text-muted">天津市塘沽开发区</span></li>
					</ul>
				</div>

			</aside>
			<!-- /Sidebar -->

		</div>
	</div>	<!-- /container -->
	
	<!-- 底部 -->

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
								Copyright &copy; 2014,Inspiration. Designed by <a href="#" rel="designer">Keen Cloud</a> 
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
		<script type="text/javascript">
	function setHeight(){
		//获取row的高度
		var height1=document.getElementById("row1").offsetHeight;
		//获取所有div
		var itemlist=document.getElementsByClassName("item1");
		for(var i=0;i<itemlist.length;i++){
			itemlist[i].style.height=height1+"px";
		}
		var height2=document.getElementById("row2").offsetHeight;
		itemlist=document.getElementsByClassName("item2");
		for(var i=0;i<itemlist.length;i++){
			itemlist[i].style.height=height2+"px";
		}
	}
	window.onload=function(){
		setHeight();
	};
	</script>
</body>
</html>