package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bus;
import com.example.demo.model.BusBooking;
import com.example.demo.repo.BusBookingRepo;
import com.example.demo.repo.BusRepo;

@Service
public class BusBookingService {

    @Autowired
    private BusRepo busRepo;

    @Autowired
    private BusBookingRepo busBookingRepo;

    // Method to create a bus booking
    public BusBooking createBusBooking(Long busId, LocalDate departureDate, LocalDate returnDate) {
        if (departureDate == null || returnDate == null || departureDate.isAfter(returnDate)) {
            throw new RuntimeException("Invalid dates provided");
        }

        Bus bus = busRepo.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus with ID " + busId + " not found"));

        BusBooking busBooking = new BusBooking();
        busBooking.setId(System.currentTimeMillis()); // Simple unique ID generation for demonstration
        busBooking.setBus(bus);
        busBooking.setDepartureDate(departureDate);
        busBooking.setReturnDate(returnDate);

        return busBookingRepo.save(busBooking);
    }

    // Method to get all bus bookings
    public List<BusBooking> getAllBookings() {
        return busBookingRepo.findAll();
    }

    // Method to get bookings by bus ID
    public List<BusBooking> getBookingsByBus(Long busId) {
        return busBookingRepo.findByBusId(busId);
    }

    // Method to delete a booking
    public void deleteBooking(Long id) {
        busBookingRepo.deleteById(id);
    }

    // Method to get a bus by ID
    public Bus getBusById(Long id) {
        return busRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus with ID " + id + " not found"));
    }

    // Method to get all buses
    public List<Bus> getAllBuses() {
        return busRepo.findAll();
    }
}
