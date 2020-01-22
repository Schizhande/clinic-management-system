package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Prescription;

@Repository
public class PrescriptionDAOImpl implements PrescriptionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void savePrescription(Prescription pre) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(pre);
	}

	@Override
	public Prescription getPrescribeDrug(int drugID, int recordID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Prescription.class);
		criteria.add(Restrictions.eq("drug.drugID", drugID));
		criteria.add(Restrictions.eq("record.recordID", recordID));
		return (Prescription)criteria.uniqueResult();
	}

	@Override
	public void removeDrug(int drugId, int recordId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Prescription.class);
		criteria.add(Restrictions.eq("drug.drugID", drugId));
		criteria.add(Restrictions.eq("record.recordID", recordId));
		currentSession.delete((Prescription)criteria.uniqueResult());
	}

	@Override
	public List<Prescription> viewPrescription(int recordId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Prescription.class);
		criteria.add(Restrictions.eq("record.recordID", recordId));
		List<Prescription> pres=((List<Prescription>)criteria.list());
		if(pres!=null){
			for(Prescription p: pres){
				Hibernate.initialize(p.getDrug());
			}
		}
		return pres;
	}

}
