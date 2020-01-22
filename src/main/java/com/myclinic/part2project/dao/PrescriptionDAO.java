package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Prescription;

public interface PrescriptionDAO {

	void savePrescription(Prescription pre);

	Prescription getPrescribeDrug(int drugID, int recordID);

	void removeDrug(int drugId, int recordId);

	List<Prescription> viewPrescription(int recordId);

}
