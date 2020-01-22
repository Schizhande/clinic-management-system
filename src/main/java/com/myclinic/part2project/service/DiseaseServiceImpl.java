package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.DiseaseDAO;
import com.myclinic.part2project.model.Disease;
import com.myclinic.part2project.model.RecordDisease;

@Service
@Transactional
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	DiseaseDAO diseaseDao;
	
	@Override
	public Disease getDiseaseBySymptom(String vertex) {
		return diseaseDao.getDiseaseBySymptom(vertex);
	}

	@Override
	public List<Disease> getDiseases() {
		 
		return diseaseDao.getDiseases();
	}

	@Override
	public void saveRecordDisease(RecordDisease rd) {
		diseaseDao.saveRecordDisease(rd);
	}

}
