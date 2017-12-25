<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.database.Chatlog"%>
<%@page import="com.service.ChatlogSevice"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String s1 = request.getParameter("userid1");
	String s2 = request.getParameter("userid2");
	int uid1 = Integer.parseInt(s1);
	int uid2 = Integer.parseInt(s2);
	
	ChatlogSevice service = new ChatlogSevice();
	Chatlog[] rst =  service.findChatlogByUserId(uid1, uid2);
	Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmss").create();
	
	String sss1 = gson.toJson(rst,Chatlog[].class);
	out.print(sss1);
	
%>

