package com.myclinic.part2project.dao;

import com.myclinic.part2project.model.User;

public interface UserDao {

	void save(User user);
	
	User findById(int id);
	
	User findByUsername(String username);
	
}

