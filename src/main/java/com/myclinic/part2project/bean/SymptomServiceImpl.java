package com.myclinic.part2project.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.model.Question;
import com.myclinic.part2project.model.Symptom;

@Service
@Transactional
public class SymptomServiceImpl implements SymptomService {

	@Autowired
	SymptomDAO symptomDao;
	
	@Override
	public List<Question> getQuestions() {
		return symptomDao.getQuestions();
	}

	@Override
	public List<Symptom> getSymptoms() {
		return symptomDao.getSymptoms();
	}

	@Override
	public Symptom getSymptomByName(String answer) {
		return symptomDao.getSymptomByName(answer);
	}

}
