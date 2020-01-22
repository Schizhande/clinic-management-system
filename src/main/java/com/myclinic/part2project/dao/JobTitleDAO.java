package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.JobTitle;

public interface JobTitleDAO {

	List<JobTitle> getJobTitles();

	JobTitle getJobTitle(int id);

}
