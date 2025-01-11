package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service

public class UserService {
	
	@Autowired
	private final UserRepo userRepo;
	
	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
	
	public Optional<User> getUserbyId(Long id){
		return userRepo.findById(id);
	}
	
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}
	
	 

}
