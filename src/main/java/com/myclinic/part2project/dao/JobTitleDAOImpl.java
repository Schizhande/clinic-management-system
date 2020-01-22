package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.JobTitle;

@Repository
public class JobTitleDAOImpl implements JobTitleDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<JobTitle> getJobTitles() {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria crit =  currentSession.createCriteria(JobTitle.class);
		return (List<JobTitle>) crit.list();
	}

	@Override
	public JobTitle getJobTitle(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		JobTitle jobTitle= currentSession.get(JobTitle.class, id);
		return jobTitle;
	}

}
