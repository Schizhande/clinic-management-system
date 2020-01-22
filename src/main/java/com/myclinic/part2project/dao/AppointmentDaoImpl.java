package com.myclinic.part2project.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Appointment;
import com.myclinic.part2project.model.Patient;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveAppointment(Appointment appointment) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(appointment);

	}

	@Override
	public List<Appointment> getDoctorAppointmentPerDay(LocalDate tempDate, int doctorId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("appointmentDate", tempDate));
		criteria.add(Restrictions.eq("doctor.staffID", doctorId));
		List<Appointment> appoints = (List<Appointment>) criteria.list();
		return appoints;
	}

	@Override
	public List<Appointment> getAppointments() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Appointment> theQuery = currentSession.createQuery("from Appointment ", Appointment.class);
		// execute query and get result list
		List<Appointment> app = theQuery.getResultList();
		if(app!=null){
			for(Appointment ap: app){
				Hibernate.initialize(ap.getDoctor());
				System.out.println(">>>>>>>>>>>>>>>"+ ap.getPatient());
				Hibernate.initialize(ap.getPatient());
			}
			
		}

		return app;
	}

	@Override
	public Appointment getAppointment(int appointID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Appointment app= currentSession.get(Appointment.class, appointID);
		return app;
	}

	@Override
	public Appointment getAppointmentByPatientId(int patientID, int scheduleId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("patient.patientID",patientID));
		criteria.add(Restrictions.eq("schedule.scheduleID",scheduleId));
		Appointment appoint =  (Appointment) criteria.uniqueResult();
		return appoint;
	}

	@Override
	public void deleteAppointment(int appointmentId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Appointment app= currentSession.get(Appointment.class, appointmentId);	
		currentSession.delete(app);
	}

	@Override
	public List<Appointment> appointmentListPerDoctor(int staffID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Appointment.class);
		criteria.add(Restrictions.eq("doctor.staffID",staffID));
		criteria.add(Restrictions.eq("appointmentDate",LocalDate.now()));
		List<Appointment> appoint =  (List<Appointment>) criteria.list();
		if(!appoint.isEmpty()){
			for(Appointment ap: appoint){
				Hibernate.initialize(ap.getDoctor());
				Hibernate.initialize(ap.getPatient());
			}
		}
		
		return appoint;
	}

}
