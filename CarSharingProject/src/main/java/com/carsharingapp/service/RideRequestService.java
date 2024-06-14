package com.carsharingapp.service;

import com.carsharingapp.domain.Location;
import com.carsharingapp.domain.RideRequest;
import com.carsharingapp.repository.RideRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class RideRequestService {

    @Autowired
    private RideRequestRepository rideRequestRepository;

    public List<RideRequest> findAll() {
        return rideRequestRepository.findAll();
    }

    public RideRequest findById(Long id) {
        return rideRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("RideRequest not found"));
    }

    public RideRequest save(RideRequest rideRequest) {
        return rideRequestRepository.save(rideRequest);
    }

    public List<RideRequest> findByFromAndToAndDepartureTime(Location from, Location to, ZonedDateTime departureTime) {
        return rideRequestRepository.findByFromAndToAndDepartureTime(from, to, departureTime);
    }
}
