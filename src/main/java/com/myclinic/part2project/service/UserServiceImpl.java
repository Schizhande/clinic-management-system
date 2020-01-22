package com.myclinic.part2project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myclinic.part2project.dao.UserDao;
import com.myclinic.part2project.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public void save(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	@Override
	public boolean isUserUsernameUnique(Integer userID, String username) {
		User user = findByUsername(username);
		return ( user == null || ((userID != null) && (user.getUserID()==userID)));
	}

	@Override
	public void updateUser(User user) {
		User use=dao.findById(user.getUserID());
		 if(use!=null){
			 use.setPassword(passwordEncoder.encode(user.getPassword()));
			 use.setUsername(user.getUsername());
		 }
		
	}
	
}
