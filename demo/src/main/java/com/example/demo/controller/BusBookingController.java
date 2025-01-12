package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bus;
import com.example.demo.model.BusBooking;
import com.example.demo.service.BusBookingService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/buses")
public class BusBookingController {

    @Autowired
    private BusBookingService busBookingService;

    // Endpoint to create a bus booking
    @PostMapping("/bookings")
    public ResponseEntity<BusBooking> createBooking(
            @RequestParam Long busId,
            @RequestParam LocalDate departureDate,
            @RequestParam LocalDate returnDate) {
        try {
            BusBooking booking = busBookingService.createBusBooking(busId, departureDate, returnDate);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to get a bus by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) {
        try {
            Bus bus = busBookingService.getBusById(id);
            return ResponseEntity.ok(bus);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to get all buses
    @GetMapping
    public List<Bus> getAllBuses() {
        return busBookingService.getAllBuses();
    }

    // Endpoint to get all bookings
    @GetMapping("/bookings")
    public List<BusBooking> getAllBookings() {
        return busBookingService.getAllBookings();
    }

    // Endpoint to get bookings by bus ID
    @GetMapping("/bookings/bus/{busId}")
    public List<BusBooking> getBookingsByBus(@PathVariable Long busId) {
        return busBookingService.getBookingsByBus(busId);
    }

    // Endpoint to delete a booking by ID
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            busBookingService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
