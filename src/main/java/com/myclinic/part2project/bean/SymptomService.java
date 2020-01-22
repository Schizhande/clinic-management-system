package com.myclinic.part2project.bean;

import java.util.List;

import com.myclinic.part2project.model.Question;
import com.myclinic.part2project.model.Symptom;


public interface SymptomService {
	public List<Question> getQuestions();
	public List<Symptom> getSymptoms();
	public Symptom getSymptomByName(String answer);

}
