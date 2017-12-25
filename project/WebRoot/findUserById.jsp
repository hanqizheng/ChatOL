<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.database.Userlist"%>
<%@page import="com.service.UserService"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String idString = request.getParameter("id");
	int id = Integer.parseInt(idString);
	
	UserService service = new UserService();
	Userlist user = service.findById(id);
	Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmsss").create();
	String s = gson.toJson(user,Userlist[].class);
	out.print(s);
%>

