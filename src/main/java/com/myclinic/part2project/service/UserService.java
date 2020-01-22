package com.myclinic.part2project.service;

import com.myclinic.part2project.model.User;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findByUsername(String username);


	boolean isUserUsernameUnique(Integer userID, String username);

	void updateUser(User user);
	
}