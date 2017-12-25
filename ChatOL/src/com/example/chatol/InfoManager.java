package com.example.chatol;

import java.util.ArrayList;

public class InfoManager {
	public static User users[];//存放所有用户的数组
	public static User finalUsers[];//存放出自己以外所有用户的数组
	
	public static ArrayList<ChatLog> chatlogs = new ArrayList<ChatLog>();

	//这里chatLog每一个元素用来存放   一组(就是我和谁聊天)  聊天记录
	public static ArrayList<ChatLog> lastChatlog = new ArrayList<ChatLog>();
	//用来存放 与每个好友聊天记录的 最后一句chatlog
	
	public static int userId;//用来存放用户自己的id
	public static User me;//用来存放用户自己的User对象
	public static int oppositeId;//用来存放与用户聊天好友的Id;
}
