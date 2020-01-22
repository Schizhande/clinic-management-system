package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Patient;

@Repository
public class PatientDAOImpl implements PatientDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void savePatient(Patient patient) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(patient);
	}

	@Override
	public List<Patient> getPatients() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Patient> theQuery = currentSession.createQuery("from Patient ", Patient.class);
		List<Patient> patients = theQuery.getResultList();
		for(Patient patient: patients){
			Hibernate.initialize(patient.getUser());
			Hibernate.initialize(patient.getGender());
			Hibernate.initialize(patient.getMaritalStatus());
		}

		return patients;
	}

	@Override
	public Patient getPatient(int patientId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Patient patient= currentSession.get(Patient.class, patientId);
		if(patient!=null){
			Hibernate.initialize(patient.getUser());
			Hibernate.initialize(patient.getGender());
			Hibernate.initialize(patient.getMaritalStatus());
		}
		return patient;
	}

	@Override
	public void deletePatient(int patientId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Patient patient= currentSession.get(Patient.class, patientId);
		currentSession.delete(patient);
	}

	@Override
	public Patient findByUserId(int userID) {
		Session session =sessionFactory.getCurrentSession();
		Query theQuery= session.createQuery("from Patient p WHERE p.user.userID=:id");
		theQuery.setParameter("id",userID);
		Patient patient=(Patient)theQuery.getSingleResult() ;
		if(patient!=null){
			Hibernate.initialize(patient.getUser());
			Hibernate.initialize(patient.getMaritalStatus());
			Hibernate.initialize(patient.getGender());
		}
		return patient;
	}

}
