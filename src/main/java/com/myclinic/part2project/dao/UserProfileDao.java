package com.myclinic.part2project.dao;

import java.util.List;

import com.myclinic.part2project.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
