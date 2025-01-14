package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Property;
import com.example.demo.model.User;
import com.example.demo.repo.PropertyRepo;
import com.example.demo.repo.UserRepo;

@Service
public class PropertyService {
	@Autowired
	private PropertyRepo propertyRepo;
	@Autowired
	private UserRepo userRepo;
	
	public Property createProperty(Property property, Long UserId) {
		User owner = userRepo.findById(UserId)
								.orElseThrow(()->new RuntimeException("User Not Found"));
		property.setOwner(owner);
		return propertyRepo.save(property);
		
	}
	
	public List<Property> getAllProperties(){
		return propertyRepo.findAll();
	}

	public Property getPropertyById(Long id) {
		return propertyRepo.findById(id)
				.orElseThrow(()->new RuntimeException("Property Not Found"));
	}

}
