package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Property;
@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {

}
