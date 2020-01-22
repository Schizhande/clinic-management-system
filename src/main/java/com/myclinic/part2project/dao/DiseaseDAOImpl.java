package com.myclinic.part2project.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Disease;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.RecordDisease;

@Repository
public class DiseaseDAOImpl implements DiseaseDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	private Logger myLogger= Logger.getLogger(this.getClass().getName());

	@Override
	public Disease getDiseaseBySymptom(String disease) {
		Session session = sessionFactory.getCurrentSession();
		Query theQuery = session.createQuery("from Disease d WHERE d=:id");
		theQuery.setParameter("id", disease);
		return (Disease) theQuery.getSingleResult();
	}

	@Override
	public List<Disease> getDiseases() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria =session.createCriteria(Disease.class);
		List<Disease> diseases=(List<Disease>)criteria.list();
		myLogger.info(">>>>>>"+ diseases);
		return diseases;
	}

	@Override
	public void saveRecordDisease(RecordDisease rd) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rd);
	}

}
