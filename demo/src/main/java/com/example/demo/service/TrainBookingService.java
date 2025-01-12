package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Train;
import com.example.demo.model.TrainBooking;
import com.example.demo.repo.TrainBookingRepo;
import com.example.demo.repo.TrainRepo;

@Service
public class TrainBookingService {

    @Autowired
    private TrainRepo trainRepo;

    @Autowired
    private TrainBookingRepo trainBookingRepo;

    // Method to create a train booking
    public TrainBooking createTrainBooking(Long trainId, LocalDate departureDate, LocalDate returnDate) {
        if (departureDate == null || returnDate == null || departureDate.isAfter(returnDate)) {
            throw new RuntimeException("Invalid dates provided");
        }

        Train train = trainRepo.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train with ID " + trainId + " not found"));

        TrainBooking trainBooking = new TrainBooking();
        trainBooking.setId(System.currentTimeMillis()); // Simple unique ID generation for demonstration
        trainBooking.setTrain(train);
        trainBooking.setDepartureDate(departureDate);
        trainBooking.setReturnDate(returnDate);

        return trainBookingRepo.save(trainBooking);
    }

    // Method to get all train bookings
    public List<TrainBooking> getAllBookings() {
        return trainBookingRepo.findAll();
    }

    // Method to get bookings by train ID
    public List<TrainBooking> getBookingsByTrain(Long trainId) {
        return trainBookingRepo.findByTrainId(trainId);
    }

    // Method to delete a booking
    public void deleteBooking(Long id) {
        trainBookingRepo.deleteById(id);
    }

    // Method to get a train by ID
    public Train getTrainById(Long id) {
        return trainRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Train with ID " + id + " not found"));
    }

    // Method to get all trains
    public List<Train> getAllTrains() {
        return trainRepo.findAll();
    }
}