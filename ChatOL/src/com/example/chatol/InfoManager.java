package com.example.chatol;

import java.util.ArrayList;

public class InfoManager {
	public static User users[];//��������û�������
	public static User finalUsers[];//��ų��Լ����������û�������
	
	public static ArrayList<ChatLog> chatlogs = new ArrayList<ChatLog>();

	//����chatLogÿһ��Ԫ���������   һ��(�����Һ�˭����)  �����¼
	public static ArrayList<ChatLog> lastChatlog = new ArrayList<ChatLog>();
	//������� ��ÿ�����������¼�� ���һ��chatlog
	
	public static int userId;//��������û��Լ���id
	public static User me;//��������û��Լ���User����
	public static int oppositeId;//����������û�������ѵ�Id;
}
