package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Comment;
import com.myclinic.part2project.model.ExaminedBy;
import com.myclinic.part2project.model.Record;

public interface RecordService {

	int saveRecord(Record record);

	List<Record> getPreviousRecord(int patientId);

	Record getRecordById(int i);


	List<Record> getWaitingList();

	void saveComment(Comment comment);

	void closeRecord(int recordId);
	
	boolean hasPandingVisit(int patientId);

	List<Record> viewAllPatientRecords();

	boolean isMapped(int recordId, int doctorId);

	void saveExaminedBy(ExaminedBy examinedBy);

	boolean isDiseaseMapped(int diseaseID, int recordID);

	boolean isSymptomMapped(int symptomID, int recordID);

	void reAddToWaiting(int recordId);

}
