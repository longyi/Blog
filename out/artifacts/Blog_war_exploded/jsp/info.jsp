
<%@ page import="Entity.Article" %>
<%@ page import="daoImpl.userDaoImpl" %>
<%@ page import="Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    userDaoImpl userDao = new userDaoImpl();
    User user = userDao.getuserbyid((Integer) session.getAttribute("user_id"));
    request.setAttribute("user",user);
%>
<!DOCTYPE html>

<head>
    <!-- META DATA -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="description" content="Free Bootstrap Themes by HTML5XCSS3 dot com - Free Responsive Html5 Templates">
    <meta name="author" content="#">

    <title>登录与注册</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css">

    <!-- Owl Carousel Assets -->
    <link href="../owl-carousel/owl.carousel.css" rel="stylesheet">
    <!-- <link href="owl-carousel/owl.theme.css" rel="stylesheet"> -->

    <!-- Custom CSS -->
    <link rel="stylesheet" href="../css/style.css">

    <!-- Custom Fonts -->
    <link rel="stylesheet" href="../font-awesome-4.4.0/css/font-awesome.min.css" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Asap:400,700' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="../js/html5shiv.js"></script>
    <script src="../js/respond.min.js"></script>
    <![endif]-->

</head>

<body class="sub-page">
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
<header class="container">
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
<div id="page-content" class="single-page">
    <div class="container">
        <div class="row">
            <div id="main-content">
                <article>
                    <div class="art-content">
                        <div class="row">
                            <div class="hero-unit">
                                <h3>更新个人数据</h3>
                                <form  method="post" action="../updateinfo">
                                    <div class="row">
                                        <div class="col-md-6">
                                            账号：
                                            <div class="form-group">
                                                <input type="text" class="form-control input-lg" name="username" id="username" value="${user.username}" placeholder="输入账号" required="required" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                          密码：
                                            <div class="form-group">
                                                <input type="password" class="form-control input-lg" name="pwd" id="pwd" value="${user.pwd}" placeholder="输入密码" required="required" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                           电话号码：
                                            <div class="form-group">
                                                <input type="text" class="form-control input-lg" name="phone" id="phone" value="${user.phone}" placeholder="输入电话号码" required="required" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                          QQ：
                                            <div class="form-group">
                                                <input type="text" class="form-control input-lg" name="qq" id="qq" value="${user.qq}" placeholder="输入QQ" required="required" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6">
                                            邮箱：
                                            <div class="form-group">
                                                <input type="text" class="form-control input-lg" name="email" id="email" value="${user.email}" placeholder="输入Email" required="required" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            个人简介：
                                            <div class="form-group">
                                            <input type="text" class="form-control input-lg" name="info" id="info" value="${user.info}" placeholder="暂无个人简介" required="required" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <button type="submit" class="btn btn-skin btn-block" >更新
                                            </button>
                                        </div>
                                    </div>
                                </form>
                                <br>
                                <div class="row">
                                <div class="col-md-12">
                                 <a href="index.jsp"> <button type="submit" class="btn btn-skin btn-block" >返回
                                    </button></a>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </article>
            </div>
        </div>
    </div>
</div>


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
</Dhtml>