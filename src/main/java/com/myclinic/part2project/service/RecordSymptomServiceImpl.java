package com.myclinic.part2project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.RecordSymptomDAO;
import com.myclinic.part2project.model.RecordSymptom;

@Service
@Transactional
public class RecordSymptomServiceImpl implements RecordSymptomService {

	@Autowired
	RecordSymptomDAO recordSymptomDao;
	
	@Override
	public void saveSymptom(RecordSymptom symptoms) {
		recordSymptomDao.saveSymptom(symptoms);
	}

}
