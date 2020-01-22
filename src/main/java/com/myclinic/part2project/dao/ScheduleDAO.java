package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Schedule;

public interface ScheduleDAO {

	void saveSchedule(Schedule schedule);

	List<Schedule> getSchedule();

	Schedule getSchedule(int scheduleId);

	void deleteSchedule(int scheduleID);

}
