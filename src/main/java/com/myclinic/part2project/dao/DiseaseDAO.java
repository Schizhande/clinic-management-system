package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Disease;
import com.myclinic.part2project.model.RecordDisease;

public interface DiseaseDAO {

	Disease getDiseaseBySymptom(String disease);

	List<Disease> getDiseases();

	void saveRecordDisease(RecordDisease rd);

}
