package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.UserProfile;

@Repository
public class DrugDAOImpl implements DrugDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveDrug(Drug drug) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(drug);
	}

	@Override
	public List<Drug> getDrugs() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Drug> theQuery = currentSession.createQuery("from Drug ", Drug.class);
		List<Drug> drugs = theQuery.getResultList();
		return drugs;
	}

	@Override
	public Drug getDrug(int drugId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Drug drug= currentSession.get(Drug.class,drugId);
		return drug;
	}

	@Override
	public Drug getDrugByName(String drugName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria crit =  currentSession.createCriteria(Drug.class);
		crit.add(Restrictions.eq("name", drugName));
		return (Drug) crit.uniqueResult();
	}

}
