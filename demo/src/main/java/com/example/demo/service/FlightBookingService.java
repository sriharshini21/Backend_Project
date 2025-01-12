package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightBooking;
import com.example.demo.repo.FlightBookingRepo;
import com.example.demo.repo.FlightRepo;

@Service
public class FlightBookingService {
	
	@Autowired
    private FlightRepo flightRepo;

    @Autowired
    private FlightBookingRepo flightBookingRepo;
    
    


    public FlightBooking createFlightBooking(Long flightId, LocalDate departureDate, LocalDate returnDate) {
        if (departureDate == null || returnDate == null || departureDate.isAfter(returnDate)) {
            throw new RuntimeException("Invalid dates provided");
        }

        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight with ID " + flightId + " not found"));

        FlightBooking flightBooking = new FlightBooking();
        flightBooking.setFlight(flight);
        flightBooking.setDepartureDate(departureDate);
        flightBooking.setReturnDate(returnDate);

        return flightBookingRepo.save(flightBooking);
    }

    public List<FlightBooking> getAllBookings() {
        return flightBookingRepo.findAll();
    }

    public List<FlightBooking> getBookingsByFlight(Long flightId) {
        return flightBookingRepo.findByFlightId(flightId);
    }

    public FlightBooking getBookingById(Long id) {
        return flightBookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight booking with ID " + id + " not found"));
    }

    public void deleteBooking(Long id) {
        flightBookingRepo.deleteById(id);
    }

	public Flight getFlightById(Long id) {
		return flightRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight with ID " + id + " not found"));
	}
	

    // Method to get all flights
    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }
}

