package com.myclinic.part2project.bean;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Question;
import com.myclinic.part2project.model.Symptom;

@Repository
public class SymptomDAOImpl implements SymptomDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	public List<Question> getQuestions() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Question> tempQuestion = currentSession.createQuery("from Question", Question.class);
		List<Question> nod = tempQuestion.getResultList();
		return nod;
	}


	public List<Symptom> getSymptoms() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Symptom> tempQuery = currentSession.createQuery("from Symptom", Symptom.class);
		List<Symptom> qu = tempQuery.getResultList();
		return qu;
	}


	@Override
	public Symptom getSymptomByName(String answer) {
		Session session =sessionFactory.getCurrentSession();
		Query theQuery= session.createQuery("from Symptom s WHERE s.symptomName=:name");
		theQuery.setParameter("name", answer);
		theQuery.getSingleResult();
		return (Symptom)theQuery.getSingleResult() ;
	}

}
