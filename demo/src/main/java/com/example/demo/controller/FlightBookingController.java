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

import com.example.demo.model.Flight;
import com.example.demo.model.FlightBooking;
import com.example.demo.service.FlightBookingService;
	
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/flights")
public class FlightBookingController {

    @Autowired
    private FlightBookingService flightService;

    @Autowired
    private FlightBookingService flightBookingService;

    // Endpoint to create a flight booking
    @PostMapping("/bookings")
    public ResponseEntity<FlightBooking> createBooking(
            @RequestParam Long flightId, 
            @RequestParam LocalDate departureDate, 
            @RequestParam LocalDate returnDate) {
        try {
            FlightBooking booking = flightBookingService.createFlightBooking(flightId, departureDate, returnDate);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to get a flight by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint to get all flights
    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    // Endpoint to get all bookings
    @GetMapping("/bookings")
    public List<FlightBooking> getAllBookings() {
        return flightBookingService.getAllBookings();
    }

    // Endpoint to get bookings by flight ID
    @GetMapping("/bookings/flight/{flightId}")
    public List<FlightBooking> getBookingsByFlight(@PathVariable Long flightId) {
        return flightBookingService.getBookingsByFlight(flightId);
    }

    // Endpoint to delete a booking by ID
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            flightBookingService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
