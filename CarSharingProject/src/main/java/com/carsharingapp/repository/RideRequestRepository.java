package com.carsharingapp.repository;

import com.carsharingapp.domain.Location;
import com.carsharingapp.domain.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.ZonedDateTime;

import java.util.List;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {
    List<RideRequest> findByFromAndToAndDepartureTime(Location from, Location to, ZonedDateTime departureTime);
}
