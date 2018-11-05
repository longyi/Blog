<%@ page import="daoImpl.articleDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: longyi
  Date: 2018/10/31
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    articleDaoImpl articleDao = new articleDaoImpl();
    int id = Integer.parseInt(request.getParameter("id"));
    articleDao.delAriticle(id);
    out.print("<script>" + "alert('删除成功');"+ "</script>");
    response.setHeader("Refresh","1;URL=archive.jsp");
%>
