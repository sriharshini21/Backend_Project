package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Bus;

@Repository
public interface BusRepo extends JpaRepository<Bus, Long> {
}