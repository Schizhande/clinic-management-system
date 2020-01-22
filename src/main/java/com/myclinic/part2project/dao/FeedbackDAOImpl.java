package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Feedback;
import com.myclinic.part2project.model.Patient;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveFeedback(Feedback feedback) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(feedback);
	}

	@Override
	public void deleteFeedback(int feedbackId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Feedback feedback = currentSession.get(Feedback.class, feedbackId);
		currentSession.delete(feedback);
	}

	@Override
	public List<Feedback> getFeedbacks() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Feedback> theQuery = currentSession.createQuery("from Feedback ", Feedback.class);
		List<Feedback> fbs = theQuery.getResultList();
		for (Feedback fb : fbs) {
			Hibernate.initialize(fb.getPatient());
		}
		return fbs;
	}

}
