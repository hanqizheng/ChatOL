<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.database.Userlist"%>
<%@page import="com.service.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	
	UserService service = new UserService();
	Userlist[] users = service.findAll();
	Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmsss").create();
	String s = gson.toJson(users,Userlist[].class);
	out.print(s);
%>

