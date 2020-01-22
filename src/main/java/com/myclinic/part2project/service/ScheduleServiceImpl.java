package com.myclinic.part2project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.ScheduleDAO;
import com.myclinic.part2project.model.Schedule;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Override
	public void saveSchedule(Schedule schedule) {
		 scheduleDAO.saveSchedule(schedule);

	}

	@Override
	public List<Schedule> getSchedule() {
		return scheduleDAO.getSchedule();
	}

	@Override
	public Schedule getSchedule(int scheduleId) {
		return scheduleDAO.getSchedule(scheduleId);
	}

	@Override
	public void deleteSchedule(int scheduleID) {
		 scheduleDAO.deleteSchedule(scheduleID);
	}
}
