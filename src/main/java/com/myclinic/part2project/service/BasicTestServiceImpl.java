package com.myclinic.part2project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.BasicTestDAO;
import com.myclinic.part2project.model.BasicTest;

@Service
@Transactional
public class BasicTestServiceImpl implements BasicTestService {

	@Autowired
	BasicTestDAO basicTestDAO;
	
	@Override
	public void saveBasicTest(BasicTest basicTest) {
		basicTestDAO.saveBasicTest(basicTest);

	}

}
