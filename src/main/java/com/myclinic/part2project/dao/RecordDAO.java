package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Comment;
import com.myclinic.part2project.model.ExaminedBy;
import com.myclinic.part2project.model.Record;
import com.myclinic.part2project.model.RecordDisease;
import com.myclinic.part2project.model.RecordSymptom;

public interface RecordDAO {

	int saveRecord(Record record);

	List<Record> getPreviousRecord(int patientId);

	Record getRecordById(int i);

	List<Record> getWaitingList();

	void saveComment(Comment comment);

	List<Record> viewAllPatientRecords();

	List<ExaminedBy> getDoctorForRecord(int recordId);

	void saveExaminedBy(ExaminedBy examinedBy);

	List<RecordDisease> getDiseaseForRecord(int recordID);

	List<RecordSymptom> getSymptomForRecord(int recordID);


}
