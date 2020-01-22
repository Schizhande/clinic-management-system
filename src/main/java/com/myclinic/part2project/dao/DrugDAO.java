package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Drug;

public interface DrugDAO {

	void saveDrug(Drug drug);

	List<Drug> getDrugs();

	Drug getDrug(int drugId);

	Drug getDrugByName(String drugName);

}
