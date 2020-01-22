package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Prescription;

public interface PrescriptionService {

	void removeDrug(int drugId, int recordId);

	void savePrecription(Prescription pre);

	Prescription getPrescribeDrug(int drugID, int recordID);

	List<Prescription> viewPrescription(int recordId);

}
