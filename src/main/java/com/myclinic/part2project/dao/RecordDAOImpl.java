package com.myclinic.part2project.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myclinic.part2project.model.Comment;
import com.myclinic.part2project.model.ExaminedBy;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.model.RecordDisease;
import com.myclinic.part2project.model.RecordSymptom;
import com.myclinic.part2project.model.Staff;

@Repository
public class RecordDAOImpl implements RecordDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Override
	public int saveRecord(Record record) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(record);
		return record.getRecordID();
	}

	@Override
	public List<Record> getPreviousRecord(int patientId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Record.class);
		criteria.add(Restrictions.eq("patient.patientID", patientId));
		List<Record> records = (List<Record>) criteria.list();
		if (records != null) {
			for (Record record : records) {
				Hibernate.initialize(record.getBasictest());
				Hibernate.initialize(record.getRecordDisease());
				Hibernate.initialize(record.getRecordSymptom());
				Hibernate.initialize(record.getPatient());
				if (record.getPatient() != null) {
					record.getPatient().getGender();
					record.getPatient().getMaritalStatus();
				}
				Hibernate.initialize(record.getExaminedBy());
				Hibernate.initialize(record.getRecordSymptom());
				Hibernate.initialize(record.getPrescription());
			}
		}
		return records;
	}

	@Override
	public Record getRecordById(int i) {
		Session currentSession = sessionFactory.getCurrentSession();
		Record record = currentSession.get(Record.class, i);
		if (record != null) {
			Hibernate.initialize(record.getBasictest());
			Hibernate.initialize(record.getRecordDisease());
			Hibernate.initialize(record.getRecordSymptom());
			Hibernate.initialize(record.getPatient());
			Hibernate.initialize(record.getExaminedBy());
			Hibernate.initialize(record.getPatient());
			Hibernate.initialize(record.getPrescription());
		}
		return record;
	}

	@Override
	public List<Record> getWaitingList() {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Record.class);
		criteria.add(Restrictions.eq("recordStatus", "Pending"));
		List<Record> records = (List<Record>) criteria.list();
		if (!records.isEmpty()) {
			for (Record reco : records) {
				Hibernate.initialize(reco.getBasictest());
				Hibernate.initialize(reco.getRecordDisease());
				Hibernate.initialize(reco.getRecordSymptom());
				Hibernate.initialize(reco.getPatient());
				if (reco.getPatient() != null) {
					Hibernate.initialize(reco.getPatient().getGender());
					Hibernate.initialize(reco.getPatient().getMaritalStatus());
				}
				Hibernate.initialize(reco.getExaminedBy());
				Hibernate.initialize(reco.getPatient());
				Hibernate.initialize(reco.getPrescription());
			}
		}
		return records;
	}

	@Override
	public void saveComment(Comment comment) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(comment);

	}

	@Override
	public List<Record> viewAllPatientRecords() {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(Record.class);
		List<Record> records = (List<Record>) criteria.list();
		if (records != null) {
			for (Record record : records) {
				Hibernate.initialize(record.getBasictest());
				Hibernate.initialize(record.getRecordDisease());
				Hibernate.initialize(record.getRecordSymptom());
				Hibernate.initialize(record.getPatient());
				Hibernate.initialize(record.getExaminedBy());
				Hibernate.initialize(record.getRecordSymptom());
				Hibernate.initialize(record.getPrescription());
			}
		}
		return records;
	}

	@Override
	public List<ExaminedBy> getDoctorForRecord(int recordId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(ExaminedBy.class);
		criteria.add(Restrictions.eq("record.recordID", recordId));
		return (List<ExaminedBy>) criteria.list();
	}

	@Override
	public void saveExaminedBy(ExaminedBy examinedBy) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(examinedBy);
	}

	@Override
	public List<RecordDisease> getDiseaseForRecord(int recordID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(RecordDisease.class);
		criteria.add(Restrictions.eq("record.recordID", recordID));
		return (List<RecordDisease>) criteria.list();
	}

	@Override
	public List<RecordSymptom> getSymptomForRecord(int recordID) {
		Session currentSession = sessionFactory.getCurrentSession();
		Criteria criteria = currentSession.createCriteria(RecordSymptom.class);
		criteria.add(Restrictions.eq("record.recordID", recordID));
		return (List<RecordSymptom>) criteria.list();
	}

}
