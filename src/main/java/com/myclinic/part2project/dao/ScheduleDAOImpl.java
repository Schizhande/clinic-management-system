package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Schedule;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveSchedule(Schedule schedule) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(schedule);
	}

	@Override
	public List<Schedule> getSchedule() {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Schedule> theQuery = currentSession.createQuery("from Schedule ", Schedule.class);
		List<Schedule> schedules = theQuery.getResultList();
		for(Schedule sch: schedules){
			Hibernate.initialize(sch.getDoctor());
			if(sch.getDoctor()!=null){
				Hibernate.initialize(sch.getDoctor().getGender());
				Hibernate.initialize(sch.getDoctor().getSpecialty());
			}
		}
		return schedules;
	}

	@Override
	public Schedule getSchedule(int scheduleId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Schedule schedule=currentSession.get( Schedule.class,scheduleId);
		return schedule;
	}

	@Override
	public void deleteSchedule(int scheduleID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Schedule schedule= currentSession.get(Schedule.class, scheduleID);
		currentSession.delete(schedule);
	}
}
