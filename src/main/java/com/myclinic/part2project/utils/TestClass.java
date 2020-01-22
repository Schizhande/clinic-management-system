package com.myclinic.part2project.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.User;

@Transactional
public class TestClass {
	
	@Autowired
	private static SessionFactory sessionFactory;
	
	public static void main(String[] arg){
		Session currentSession= sessionFactory.getCurrentSession();
		User patient= currentSession.get(User.class, 10);
		currentSession.delete(patient);
	}

}
