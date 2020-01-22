package com.myclinic.part2project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.BasicTest;

@Repository
public class BasicTestDAOImpl implements BasicTestDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveBasicTest(BasicTest basicTest) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(basicTest);
	}

}
