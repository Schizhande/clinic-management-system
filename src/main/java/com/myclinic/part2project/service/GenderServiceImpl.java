package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.GenderDAO;
import com.myclinic.part2project.model.Gender;
import com.myclinic.part2project.model.MaritalStatus;

@Service
@Transactional
public class GenderServiceImpl implements GenderService {

	@Autowired
	GenderDAO genderDao;

	@Override
	public List<Gender> getGenders() {
		return genderDao.getGenders();
	}

	@Override
	public List<MaritalStatus> getMaritals() {
		return genderDao.getMaritals();
	}

}
