package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Property;
import com.example.demo.service.PropertyService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/properties")
public class PropertyController {
	
	@Autowired
	private PropertyService propertyService;
	
	@PostMapping
	public Property createProperty(@RequestBody Property property,@RequestParam Long UserId) {
		return propertyService.createProperty(property, UserId);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Property> getPropertyById(@PathVariable Long id){
		Property property = propertyService.getPropertyById(id);
		return ResponseEntity.ok(property);
	}
	
	   @GetMapping 
	    public ResponseEntity<List<Property>> getAllProperties() { 
	        List<Property> properties = propertyService.getAllProperties();
	        return new ResponseEntity<>(properties, HttpStatus.OK);
	    }
}
