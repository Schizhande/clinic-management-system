package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Disease;
import com.myclinic.part2project.model.RecordDisease;

public interface DiseaseService {

	Disease getDiseaseBySymptom(String vertex);

	List<Disease> getDiseases();

	void saveRecordDisease(RecordDisease rd);

}
