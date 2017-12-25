package com.service;

import java.util.List;

import org.hibernate.Transaction;

import com.database.HibernateSessionFactory;
import com.database.Userlist;
import com.database.UserlistDAO;

public class UserService {
	public Userlist[] findAll(){
		UserlistDAO udao = new UserlistDAO();
		List<Userlist> list = udao.findAll();
		HibernateSessionFactory.closeSession();
		return list.toArray(new Userlist[0]);
	}
	
	public Userlist findByName(String name){
		return null;
	}
	
	public Userlist findById(int id){
		UserlistDAO udao = new UserlistDAO();
		Userlist u = udao.findById(id);
		HibernateSessionFactory.closeSession();
		return u;
	}
	
	public String addUser(String username,String password,String nickname){
		UserlistDAO udao = new UserlistDAO();
		Transaction tx = udao.getSession().beginTransaction();
		try {
			Userlist user = new Userlist(username,password,nickname);
			udao.save(user);
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
