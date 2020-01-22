package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Patient;

public interface PatientService {

	void savePatient(Patient patient);

	List<Patient> getPatients();

	Patient getPatient(int patientId);

	void deletePatient(int patientId);

	Patient findByUserId(int userID);

	 
	
}
