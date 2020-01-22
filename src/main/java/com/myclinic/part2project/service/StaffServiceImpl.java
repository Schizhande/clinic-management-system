package com.myclinic.part2project.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.JobTitleDAO;
import com.myclinic.part2project.dao.StaffDAO;
import com.myclinic.part2project.model.JobTitle;
import com.myclinic.part2project.model.Staff;
import com.myclinic.part2project.model.UserProfile;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffDAO staffDAO;

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	JobTitleDAO jobTitleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveStaff(Staff staff) {

		Staff sta = staffDAO.getStaff(staff.getStaffID());
		if (sta != null) {
			sta.setAddress(staff.getAddress());
			sta.getUser().setPassword(passwordEncoder.encode(staff.getUser().getPassword()));
			sta.getUser().setUsername(staff.getUser().getUsername());
			sta.setDob(staff.getDob());
			sta.setEmail(staff.getEmail());
			sta.setFirstName(staff.getFirstName());
			sta.setJobTitle(staff.getJobTitle());
			sta.setGender(staff.getGender());
			sta.setJoinedDate(staff.getJoinedDate());
			sta.setLastName(staff.getLastName());
			sta.setMaritalStatus(staff.getMaritalStatus());
			sta.setSpecialty(staff.getSpecialty());
		} else {
			staff.getUser().setPassword(passwordEncoder.encode(staff.getUser().getPassword()));
			Set<UserProfile> profiles = new HashSet<>();
			JobTitle jot=jobTitleDao.getJobTitle(staff.getJobTitle().getId());
			UserProfile userP = userProfileService.findByType(jot.getTitle().toUpperCase());
			profiles.add(userP);
			staff.getUser().setUserProfiles(profiles);

			staffDAO.saveStaff(staff);
		}

	}

	@Override
	public List<Staff> getStaffs() {
		return staffDAO.getStaffs();
	}

	@Override
	public Staff getStaff(int staffId) {
		return staffDAO.getStaff(staffId);
	}

	@Override
	public void deleteStaff(int staffId) {
		staffDAO.deleteStaff(staffId);
	}

	@Override
	public List<Staff> getDoctors() {
		List<Staff> doctors = new ArrayList<Staff>();
		if (staffDAO.getStaffs().isEmpty()) {
			return doctors;
		} else {
			for (Staff doctor : staffDAO.getStaffs()) {
				if (doctor.getJobTitle().getTitle().equalsIgnoreCase("DOCTOR")) {
					doctors.add(doctor);
				}
			}
			return doctors;
		}

	}

	@Override
	public Staff getStaffByUserId(int userID) {
		return staffDAO.getStaffByUserId(userID);
	}

}
