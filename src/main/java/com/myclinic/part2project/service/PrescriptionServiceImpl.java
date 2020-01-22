package com.myclinic.part2project.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.PrescriptionDAO;
import com.myclinic.part2project.model.Drug;
import com.myclinic.part2project.model.Prescription;
import com.myclinic.part2project.model.Record;

@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	PrescriptionDAO prescriptionDao;

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Override
	public void removeDrug(int drugId, int recordId) {
		prescriptionDao.removeDrug(drugId,recordId);
	}

	@Override
	public void savePrecription(Prescription pre) {
		prescriptionDao.savePrescription(pre);
		
	}

	@Override
	public Prescription getPrescribeDrug(int drugID, int recordID) {
		return prescriptionDao.getPrescribeDrug(drugID, recordID);
	}

	@Override
	public List<Prescription> viewPrescription(int recordId) {
		return prescriptionDao.viewPrescription(recordId);
	}

}
