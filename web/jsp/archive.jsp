<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="daoImpl.articleDaoImpl" %>
<%@ page import="Entity.Article" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <!-- META DATA -->
	<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="Free Bootstrap Themes by HTML5XCSS3 dot com - Free Responsive Html5 Templates">
    <meta name="author" content="#">

	<title>博客</title>
  
	<!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css"  type="text/css">
	
	<!-- Owl Carousel Assets -->
    <link href="../owl-carousel/owl.carousel.css" rel="stylesheet">
    <!-- <link href="owl-carousel/owl.theme.css" rel="stylesheet"> -->
	
	<!-- Custom CSS -->
    <link rel="stylesheet" href="../css/style.css">
	
	<!-- Custom Fonts -->
    <link rel="stylesheet" href="../font-awesome-4.4.0/css/font-awesome.min.css"  type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Asap:400,700' rel='stylesheet' type='text/css'>
	
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="../js/html5shiv.js"></script>
        <script src="../js/respond.min.js"></script>
    <![endif]-->
	
</head>

<body class="sub-page">	
	<!-- /////////////////////////////////////////Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="index.jsp"><%=session.getAttribute("username")%>的博客</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="page-scroll" href="index.jsp">首页</a>
                    </li>

                    <li>
                        <%
                            if (session.getAttribute("username") == null){
                        %>
                        <%
                        }
                        else
                        {
                        %>
                        <a class="page-scroll" href="archive.jsp">我的文章</a>
                        <%
                            }
                        %>

                    </li>
                    <li>
                        <%
                            if (session.getAttribute("username") == null){
                        %>
                        <%
                        }
                        else
                        {
                        %>
                        <a class="page-scroll" href="add.jsp">写文章</a>
                        <%
                            }
                        %>
                    </li>
                    <li>
                        <%
                            if (session.getAttribute("username") == null){
                        %>
                        <a class="page-scroll" href="login.jsp">登录</a>
                        <%
                        }
                        else
                        {
                        %>
                        <a href="info.jsp" class="page-scroll"><%=session.getAttribute("username")%></a>
                        <%
                            }
                        %>
                    </li>
                    <li>
                        <%
                            if (session.getAttribute("username") == null){
                        %>

                        <%
                        }
                        else
                        {
                        %>
                        <a class="page-scroll" href="logout.jsp">注销</a>
                        <%
                            }
                        %>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
	<!-- Navigation -->

	<!-- Background Gradients-->
	<div  class="site-gradients">
		<div class="site-gradients-media">
			<figure>
				<img src="Another%20Yosemite%20short%20movie%20project%20%E2%80%93%20Modern_files/PcLGXNjMTdiFVKTrElCl__DSC2245.jpg" alt="PcLGXNjMTdiFVKTrElCl__DSC2245" srcset="https://themedemos.webmandesign.eu/modern/wp-content/uploads/sites/8/2014/11/PcLGXNjMTdiFVKTrElCl__DSC2245.jpg 1920w, https://themedemos.webmandesign.eu/modern/wp-content/uploads/sites/8/2014/11/PcLGXNjMTdiFVKTrElCl__DSC2245-420x280.jpg 420w, https://themedemos.webmandesign.eu/modern/wp-content/uploads/sites/8/2014/11/PcLGXNjMTdiFVKTrElCl__DSC2245-744x497.jpg 744w, https://themedemos.webmandesign.eu/modern/wp-content/uploads/sites/8/2014/11/PcLGXNjMTdiFVKTrElCl__DSC2245-1200x801.jpg 1200w" sizes="(max-width: 1617px) 100vw, 1617px" height="1080" width="1617">
			</figure>
		</div>
	</div>
	
	
	<header class="container">
		<div class="site-branding">
			<h1 class="site-title">
				<a href="index.jsp">
					<span><%=session.getAttribute("username")%>你好</span>
				</a>
			</h1>
			<h2 class="site-description">欢迎噢!</h2>
		</div>
		<div class="social-links">
			<ul class="list-inline">
				<li><a href="#"><i class="fa fa-facebook"></i></a></li>
				<li><a href="#"><i class="fa fa-twitter"></i></a></li>
				<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
				<li><a href="#"><i class="fa fa-vimeo"></i></a></li>
				<li><a href="#"><i class="fa fa-rss"></i></a></li>
			</ul>
		</div>
	</header>
	
	<!-- /////////////////////////////////////////Content -->

	<%
		if (session.getAttribute("username") != null){
			articleDaoImpl articleDao = new articleDaoImpl();
			List<Article> articles = articleDao.findall((Integer) session.getAttribute("user_id"));
			request.setAttribute("articles",articles);
			if (articles.size()<1){

	%>
	<div class="col-md-12 text-center">
		<h1>你还没有写过文章噢</h1>
		<hr>
		<p>快去写文章吧
		</p>
	</div>
	<%
		}
		else {
	%>
	<div id="page-content"><!-- ////////////Content Box 01 -->
		<section class="box-content box-1">
			<div class="container">
					<div class="row">
						<c:forEach var="article" items="${requestScope.articles}" >
							<div class="col-md-4">
								<div class="box-item">
									<div class="content">
                                        <div style="width: 300px;height: 300px"><img src="${article.imgurl}"/></div>
										<span>标题：</span>
										<h3>${article.title}</h3>
                                        <span>作者：<span>${article.user_name}</span></span>
										<span>发布时间：</span>
										<span>${article.pub_date}</span><br>
										<a href="single.jsp?id=${article.id}">更多...</a>
										<br>
                                        <span><a class="page-scroll" href="update.jsp?id=${article.id}">编辑文章</a></span>
                                        <br>
										<span><a class="page-scroll" href="delete.jsp?id=${article.id}">删除文章</a></span>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
			</div>
		</section>
	</div>
	<%
		}
	%>
	<%
	}
	else
	{
	%>
	<div class="col-md-12 text-center">
		<h1>请先登录噢</h1>
		<hr>
		<p>登陆后查看噢
		</p>
	</div>
	<%
		}
	%>

	<footer>
		<div class="wrap-footer">
			<div class="container">
				<div class="row">
					<div class="col-footer col-md-3">
						<h2 class="footer-title">关于我</h2>
						<div class="textwidget"><%=session.getAttribute("info")%><br> <br>你好呀
						</div>
					</div>

					<div class="col-footer col-md-3">
						<h2 class="footer-title">标签</h2>
						<div class="footer-tags">
							<a href="#">摆设</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="bottom-footer">
			<div class="container">
				<div class="row">

					<div class="col-md-4">
						<ul class="list-inline social-buttons">
							<li><a href="#"><i class="fa fa-twitter"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-facebook"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-linkedin"></i></a>
							</li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a>
							</li>
						</ul>
					</div>
					<div class="col-md-4">
						<ul class="list-inline quicklinks">
							<li><a href="#"><%=session.getAttribute("username")%>的博客</a>
							</li>
							<li><a href="#">你好呀</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- jQuery -->
	<script type="text/javascript" src="../js/jquery-2.1.1.js"></script>
	<script type="text/javascript" src="../js/bootstrap.min.js"></script>
	
	<!-- Custom Theme JavaScript -->
	<script src="../js/agency.js"></script>
	<script src="../js/agency.js"></script>

	<!-- Plugin JavaScript -->
	<script src="../js/jquery.easing.min.js"></script>
	<script src="../js/classie.js"></script>
	<script src="../js/cbpAnimatedHeader.js"></script>
	
</body>
</html>