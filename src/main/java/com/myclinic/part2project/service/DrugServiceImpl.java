package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.DrugDAO;
import com.myclinic.part2project.model.Drug;

@Service
@Transactional
public class DrugServiceImpl implements DrugService {
	
	@Autowired
	DrugDAO drugDAO;

	@Override
	public void saveDrug(Drug drug) {
		drugDAO.saveDrug(drug);
	}

	@Override
	public List<Drug> getDrugs() {
		return drugDAO.getDrugs();
	}

	@Override
	public Drug getDrug(int drugId) {
		return drugDAO.getDrug(drugId);
	}

	@Override
	public Drug getDrugByName(String drugName) {
		return drugDAO.getDrugByName(drugName);
	}

}
