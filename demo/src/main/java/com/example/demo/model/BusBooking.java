package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BusBooking {

    @Id
    private Long id;

    private LocalDate departureDate;
    private LocalDate returnDate;

    @ManyToOne
    private Bus bus;
}