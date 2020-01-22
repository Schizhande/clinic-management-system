package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.Staff;

public interface StaffService {

	void saveStaff(Staff staff);


	Staff getStaff(int staffId);

	void deleteStaff(int staffId);
	
	List<Staff> getDoctors();

	Staff getStaffByUserId(int userID);

	List<Staff> getStaffs();

}
