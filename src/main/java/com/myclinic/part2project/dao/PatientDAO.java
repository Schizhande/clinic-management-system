package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Patient;

public interface PatientDAO {

	void savePatient(Patient patient);

	List<Patient> getPatients();

	Patient getPatient(int patientId);

	void deletePatient(int patientId);

	Patient findByUserId(int userID);

	 

}
