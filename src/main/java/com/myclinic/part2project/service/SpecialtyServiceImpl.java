package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.SpecialtyDAO;
import com.myclinic.part2project.model.Specialty;

@Service
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService {

	@Autowired
	SpecialtyDAO specialtyDAO;
	
	@Override
	public List<Specialty> getSpecialty() { 
		return specialtyDAO.getSpecialty();
	}

}
