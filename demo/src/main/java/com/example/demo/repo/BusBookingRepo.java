package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BusBooking;

@Repository
public interface BusBookingRepo extends JpaRepository<BusBooking, Long> {

    // Custom query to get bookings by bus ID
    List<BusBooking> findByBusId(Long busId);
}