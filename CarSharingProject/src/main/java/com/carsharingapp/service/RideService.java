package com.carsharingapp.service;

import com.carsharingapp.domain.Ride;
import com.carsharingapp.repository.RideRepository;
import com.carsharingapp.util.RideUtil; // Ensure you have the RideUtil class for password generation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> findAll() {
        return rideRepository.findAll();
    }

    public Ride findById(Long id) {
        return rideRepository.findById(id).orElseThrow(() -> new RuntimeException("Ride not found"));
    }

    public Ride save(Ride ride) {
        if (ride.getRidePassword() == null || ride.getRidePassword().isEmpty()) {
            ride.setRidePassword(RideUtil.generateRidePassword());
        }
        return rideRepository.save(ride);
    }
}
