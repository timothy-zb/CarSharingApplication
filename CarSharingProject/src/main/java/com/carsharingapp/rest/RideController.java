package com.carsharingapp.rest;

import com.carsharingapp.domain.Ride;
import com.carsharingapp.service.RideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {

    private static final Logger logger = LoggerFactory.getLogger(RideController.class);
    private final RideService rideService;

    // Constructor injection
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping
    public ResponseEntity<List<Ride>> findAll() {
        logger.info("Fetching all rides");
        List<Ride> rides = rideService.findAll();
        return ResponseEntity.ok(rides);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ride> findById(@PathVariable Long id) {
        logger.info("Fetching ride with id {}", id);
        Ride ride = rideService.findById(id);
        if (ride != null) {
            return ResponseEntity.ok(ride);
        } else {
            logger.error("Ride with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Ride> save(@RequestBody Ride ride) {
        logger.info("Saving ride");
        Ride savedRide = rideService.save(ride);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRide);
    }
}
