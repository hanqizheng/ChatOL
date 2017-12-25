package com.service;



import java.sql.Timestamp;
import java.util.Date;
import java.util.List;







import org.hibernate.Query;
import org.hibernate.Transaction;

import com.database.Chatlog;
import com.database.ChatlogDAO;
import com.database.HibernateSessionFactory;

public class ChatlogSevice {
	public Chatlog[] findChatlogByUserId(int userid1,int userid2){
		ChatlogDAO cdao = new ChatlogDAO();
		
		Query query = cdao.getSession().createQuery("from Chatlog where (userid1=:uid1 and userid2=:uid2) or (userid1=:uid2 and userid2=:uid1) ");
		query.setInteger("uid1", userid1);
		query.setInteger("uid2", userid2);
		List<Chatlog> list = query.list();
		HibernateSessionFactory.closeSession();
		
		return list.toArray(new Chatlog[0]);
	}
	
	public String addChatlog(int userid1,int userid2,String content,String memo1){
		ChatlogDAO cdao = new ChatlogDAO();
		Transaction tx = cdao.getSession().beginTransaction();
		try {
			Date date = new Date();
			Chatlog log = new Chatlog(userid1,userid2,content,new Timestamp(date.getTime()),memo1,null);
			cdao.save(log);
			
			tx.commit();
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return e.toString();
		}finally{
			HibernateSessionFactory.closeSession();
		}
	}
}
