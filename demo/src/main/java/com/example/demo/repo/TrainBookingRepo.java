package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TrainBooking;

@Repository
public interface TrainBookingRepo extends JpaRepository<TrainBooking, Long> {
    List<TrainBooking> findByTrainId(Long trainId);
}
