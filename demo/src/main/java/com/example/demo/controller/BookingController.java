package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/bookings")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public Booking createBooking(@RequestParam Long propertyId, @RequestParam LocalDate startDate,@RequestParam LocalDate endDate) {
		return bookingService.createBooking(propertyId, startDate, endDate);
	}
	
	@GetMapping("/{id}")
	public Booking getBookingbyId(@PathVariable Long id) {
		return bookingService.getBookingById(id);
	}
	
	@GetMapping
	public List<Booking> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	@GetMapping("/property/{propertyId}")
	public List<Booking> getBookingByProperty(@PathVariable Long propertyId){
		return bookingService.getBookingByProperty(propertyId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
	}

}
