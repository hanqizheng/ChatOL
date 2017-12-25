<%@page import="com.service.ChatlogSevice"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//http://publishChat.jsp?userid1=xxxx&userid2=xxxxx&content=xxxx
	String s1 = request.getParameter("userid1");
	String s2 = request.getParameter("userid2");
	String content = request.getParameter("content");
	String memo1 = request.getParameter("memo1");
	int uid1 = Integer.parseInt(s1);
	int uid2 = Integer.parseInt(s2);
	
	ChatlogSevice service = new ChatlogSevice();
	
	String sss = service.addChatlog(uid1, uid2, content, memo1);
	
	
	out.print(sss);
%>

