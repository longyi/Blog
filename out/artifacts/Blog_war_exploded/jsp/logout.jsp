<%--
  Created by IntelliJ IDEA.
  User: longyi
  Date: 2018/10/27
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.removeAttribute("username");
    session.removeAttribute("user_id");
    session.removeAttribute("pwd");
    session.removeAttribute("info");
    session.removeAttribute("email");
    session.removeAttribute("phone");
    out.print("<script>" + "alert('注销成功');"+ "</script>");
    response.setHeader("Refresh","1;URL=index.jsp");
%>
