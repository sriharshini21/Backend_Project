package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
	
	List<Booking> findByPropertyId(Long propertyId);

}
