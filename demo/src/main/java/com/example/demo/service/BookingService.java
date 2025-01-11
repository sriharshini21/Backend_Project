package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.Property;
import com.example.demo.repo.BookingRepo;
import com.example.demo.repo.PropertyRepo;

@Service
public class BookingService {

    @Autowired
    private PropertyRepo propertyRepo;

    @Autowired
    private BookingRepo bookingRepo;

    public Booking createBooking(Long id, LocalDate startDate, LocalDate endDate) {
        // Validate the start and end dates
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new RuntimeException("Invalid dates provided");
        }

        // Log the incoming property ID
        System.out.println("Looking for property with ID: " + id);

        // Fetch the property from the repository
        Property property = propertyRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Property with ID " + id + " not found"));

        // Log the fetched property
        System.out.println("Property found: " + property);

        // Create the booking
        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setStartdate(startDate);
        booking.setEnddate(endDate);

        // Save and return the booking
        return bookingRepo.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public List<Booking> getBookingByProperty(Long propertyId) {
        return bookingRepo.findByPropertyId(propertyId);
    }

    public void deleteBooking(Long id) {
        bookingRepo.deleteById(id);
    }

    public Booking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking with ID " + id + " not found"));
    }
}
