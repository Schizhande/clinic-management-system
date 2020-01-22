package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Drug;

public interface DrugService {

	void saveDrug(Drug drug);

	List<Drug> getDrugs();

	Drug getDrug(int drugId);

	Drug getDrugByName(String drugName);

}
