package com.carsharingapp.repository;

import com.carsharingapp.domain.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
}
