<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.database.Userlist"%>
<%@page import="com.service.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	String nickname = request.getParameter("nickname");
	
	
	UserService service = new UserService();
	
	String ss = service.addUser(username, password, nickname);
	
	
	out.print(ss);
%>

