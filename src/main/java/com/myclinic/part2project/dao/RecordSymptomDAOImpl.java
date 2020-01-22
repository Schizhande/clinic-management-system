package com.myclinic.part2project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.RecordSymptom;

@Repository
public class RecordSymptomDAOImpl implements RecordSymptomDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveSymptom(RecordSymptom symptoms) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(symptoms);
	}

}
