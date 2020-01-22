package com.myclinic.part2project.service;

import java.util.List;

import com.myclinic.part2project.model.UserProfile;


public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
