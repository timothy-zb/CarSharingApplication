package com.carsharingapp.rest;

import com.carsharingapp.domain.Driver;
import com.carsharingapp.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {
    private static final Logger log = LoggerFactory.getLogger(DriverController.class);
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> findAll() {
        log.info("Fetching all drivers");
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> findById(@PathVariable Long id) {
        log.info("Fetching driver with id {}", id);
        Driver driver = driverService.findById(id);
        if (driver != null) {
            return ResponseEntity.ok(driver);
        } else {
            log.error("Driver with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Driver> save(@RequestBody Driver driver) {
        try {
            Driver savedDriver = driverService.save(driver);
            log.info("Driver saved successfully with id {}", savedDriver.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDriver);
        } catch (Exception e) {
            log.error("Error saving driver: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
