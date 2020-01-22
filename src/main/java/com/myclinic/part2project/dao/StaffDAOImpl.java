package com.myclinic.part2project.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.Staff;

@Repository
public class StaffDAOImpl implements StaffDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveStaff(Staff staff) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(staff);
	}

	@Override
	public List<Staff> getStaffs() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		// create a query
		Query<Staff> theQuery = currentSession.createQuery("from Staff ", Staff.class);
		// execute query and get result list
		List<Staff> staffs = theQuery.getResultList();
		for(Staff staff:staffs){
			Hibernate.initialize(staff.getUser());
			Hibernate.initialize(staff.getGender());
			Hibernate.initialize(staff.getMaritalStatus());
			Hibernate.initialize(staff.getJobTitle());
			Hibernate.initialize(staff.getSpecialty());
		}
		return staffs;
	}

	@Override
	public Staff getStaff(int staffId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Staff staff = currentSession.get(Staff.class, staffId);
		if(staff!=null){
			Hibernate.initialize(staff.getUser());
			Hibernate.initialize(staff.getMaritalStatus());
			Hibernate.initialize(staff.getGender());
			Hibernate.initialize(staff.getJobTitle());
			Hibernate.initialize(staff.getSpecialty());
		}
		return staff;
	}

	@Override
	public void deleteStaff(int staffId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Staff staff= currentSession.get(Staff.class, staffId);
		currentSession.delete(staff);
		
	}

	@Override
	public Staff getStaffByUserId(int userID) {
		Session session =sessionFactory.getCurrentSession();
		Query theQuery= session.createQuery("from Staff s WHERE s.user.userID=:id");
		theQuery.setParameter("id",userID);
		theQuery.getSingleResult();
		Staff staff=(Staff)theQuery.getSingleResult() ;
		if(staff!=null){
			Hibernate.initialize(staff.getUser());
			Hibernate.initialize(staff.getGender());
			Hibernate.initialize(staff.getMaritalStatus());
			Hibernate.initialize(staff.getJobTitle());
			Hibernate.initialize(staff.getSpecialty());
		}
		return staff;
	}

}
