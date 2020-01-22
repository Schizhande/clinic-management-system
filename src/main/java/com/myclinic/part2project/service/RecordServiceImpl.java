package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.DrugDAO;
import com.myclinic.part2project.dao.RecordDAO;
import com.myclinic.part2project.model.Comment;
import com.myclinic.part2project.model.ExaminedBy;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.model.RecordDisease;
import com.myclinic.part2project.model.RecordSymptom;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {

	@Autowired
	RecordDAO recordDAO;

	@Autowired
	DrugDAO drugDAO;

	@Override
	public int saveRecord(Record record) {
		return recordDAO.saveRecord(record);

	}

	@Override
	public List<Record> getPreviousRecord(int patientId) {
		return recordDAO.getPreviousRecord(patientId);
	}

	@Override
	public Record getRecordById(int i) {
		return recordDAO.getRecordById(i);
	}

	@Override
	public List<Record> getWaitingList() {
		return recordDAO.getWaitingList();
	}

	@Override
	public void saveComment(Comment comment) {
		recordDAO.saveComment(comment);
	}

	@Override
	public void closeRecord(int recordId) {
		Record record = recordDAO.getRecordById(recordId);
		record.setRecordStatus("cleared");

	}

	@Override
	public boolean hasPandingVisit(int patientId) {
		List<Record> records = recordDAO.getPreviousRecord(patientId);
		for (Record rec : records) {
			if (rec.getRecordStatus().equalsIgnoreCase("pending")) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Record> viewAllPatientRecords() {
		return recordDAO.viewAllPatientRecords();
	}

	@Override
	public boolean isMapped(int recordId, int doctorId) {
		for (ExaminedBy ex : recordDAO.getDoctorForRecord(recordId)) {
			if (ex.getDoctorOrNurse().getStaffID() == doctorId) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void saveExaminedBy(ExaminedBy examinedBy) {
		recordDAO.saveExaminedBy(examinedBy);
	}

	@Override
	public boolean isDiseaseMapped(int diseaseID, int recordID) {
		for(RecordDisease rd: recordDAO.getDiseaseForRecord(recordID)){
			if(rd.getDisease().getDiseaseID()==diseaseID){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSymptomMapped(int symptomID, int recordID) {
		 for(RecordSymptom rs: recordDAO.getSymptomForRecord(recordID)){
			 if(rs.getSymptom().getSymptomID()==symptomID){
				 return true;
			 }
		 }
		return false;
	}

	@Override
	public void reAddToWaiting(int recordId) {
		Record record = recordDAO.getRecordById(recordId);
		record.setRecordStatus("Pending");
		
	}

}
