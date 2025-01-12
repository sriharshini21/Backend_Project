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

import com.example.demo.model.Train;
import com.example.demo.model.TrainBooking;
import com.example.demo.service.TrainBookingService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/trains")
public class TrainBookingController {

    @Autowired
    private TrainBookingService trainBookingService;

    // Endpoint to create a train booking
    @PostMapping("/bookings")
    public ResponseEntity<TrainBooking> createBooking(
            @RequestParam Long trainId,
            @RequestParam LocalDate departureDate,
            @RequestParam LocalDate returnDate) {
        try {
            TrainBooking booking = trainBookingService.createTrainBooking(trainId, departureDate, returnDate);
            return ResponseEntity.status(HttpStatus.CREATED).body(booking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // Endpoint to get a train by ID
    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        try {
            Train train = trainBookingService.getTrainById(id);
            return ResponseEntity.ok(train);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to get all trains
    @GetMapping
    public List<Train> getAllTrains() {
        return trainBookingService.getAllTrains();
    }

    // Endpoint to get all bookings
    @GetMapping("/bookings")
    public List<TrainBooking> getAllBookings() {
        return trainBookingService.getAllBookings();
    }

    // Endpoint to get bookings by train ID
    @GetMapping("/bookings/train/{trainId}")
    public List<TrainBooking> getBookingsByTrain(@PathVariable Long trainId) {
        return trainBookingService.getBookingsByTrain(trainId);
    }

    // Endpoint to delete a booking by ID
    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        try {
            trainBookingService.deleteBooking(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}