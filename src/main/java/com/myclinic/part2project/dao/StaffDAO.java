package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.Staff;

public interface StaffDAO {

	void saveStaff(Staff staff);

	List<Staff> getStaffs();

	Staff getStaff(int staffId);

	void deleteStaff(int staffId);

	Staff getStaffByUserId(int userID);

}
