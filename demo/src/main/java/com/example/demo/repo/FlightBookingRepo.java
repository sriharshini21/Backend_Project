package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.FlightBooking;

public interface FlightBookingRepo extends JpaRepository<FlightBooking, Long> {
    List<FlightBooking> findByFlightId(Long flightId);
}
