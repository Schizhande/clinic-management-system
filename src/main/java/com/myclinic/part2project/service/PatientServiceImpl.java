package com.myclinic.part2project.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.PatientDAO;
import com.myclinic.part2project.dao.UserProfileDao;
import com.myclinic.part2project.model.Patient;
import com.myclinic.part2project.model.UserProfile;
import com.myclinic.part2project.model.UserProfileType;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDAO patientDAO;

	@Autowired
	UserProfileDao userProfileDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void savePatient(Patient patient) {
		Patient pat = patientDAO.getPatient(patient.getPatientID());
		if (pat != null) {
			pat.setAddress(patient.getAddress());
			pat.setDob(patient.getDob());
			pat.setEmail(patient.getEmail());
			pat.setFirstName(patient.getFirstName());
			pat.setGender(patient.getGender());
			pat.setJoinedDate(patient.getJoinedDate());
			pat.setLastName(patient.getLastName());
			pat.setMaritalStatus(patient.getMaritalStatus());

		} else {
			Set<UserProfile> userProfiles = new HashSet();
			UserProfile e = userProfileDAO.findByType("PATIENT");
			if (e == null) {
				UserProfile u=new UserProfile();
				u.setType(UserProfileType.PATIENT.getUserProfileType());
				userProfiles.add(u);
			} else {
				userProfiles.add(e);
			}

			patient.getUser().setUserProfiles(userProfiles);
			patient.getUser().setPassword(passwordEncoder.encode(patient.getUser().getPassword()));
			patientDAO.savePatient(patient);
		}
	}

	@Override
	public List<Patient> getPatients() {
		return patientDAO.getPatients();
	}

	@Override
	public Patient getPatient(int patientId) {

		return patientDAO.getPatient(patientId);
	}

	@Override
	public void deletePatient(int patientId) {
		patientDAO.deletePatient(patientId);

	}

	@Override
	public Patient findByUserId(int userID) {
		return patientDAO.findByUserId(userID);
	}

}
