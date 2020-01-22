package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.JobTitleDAO;
import com.myclinic.part2project.model.JobTitle;

@Service
@Transactional
public class JobTitleServiceImpl implements JobTitleService {

	@Autowired
	JobTitleDAO jobTitleDao;
	
	@Override
	public List<JobTitle> getJobTitles() {
		return jobTitleDao.getJobTitles();
	}

}
