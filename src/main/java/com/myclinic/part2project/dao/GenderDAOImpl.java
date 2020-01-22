package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Gender;
import com.myclinic.part2project.model.MaritalStatus;

@Repository
public class GenderDAOImpl implements GenderDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Gender> getGenders() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Gender> theQuery = currentSession.createQuery("from Gender ", Gender.class);
		// execute query and get result list
		List<Gender> genders = theQuery.getResultList();
		return genders;
	}

	@Override
	public List<MaritalStatus> getMaritals() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<MaritalStatus> theQuery = currentSession.createQuery("from MaritalStatus ", MaritalStatus.class);
		// execute query and get result list
		List<MaritalStatus> maritals = theQuery.getResultList();
		return maritals;
	}

}
