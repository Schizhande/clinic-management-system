package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

 
import com.myclinic.part2project.model.Specialty;

@Repository
public class SpecialtyDAOlmpl implements SpecialtyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Specialty> getSpecialty() {
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Specialty> theQuery = currentSession.createQuery("from Specialty ", Specialty.class);
		// execute query and get result list
		List<Specialty> specialty = theQuery.getResultList();
		return specialty;
	}

}
