package com.carsharingapp.rest;

import com.carsharingapp.rest.DTO.LocationDTO;
import com.carsharingapp.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveLocation(@RequestBody LocationDTO locationDTO) {
        logger.info("Saving location: {}", locationDTO);
        locationService.saveLocation(locationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Long id) {
        logger.info("Fetching location with id {}", id);
        LocationDTO locationDTO = locationService.findLocationById(id);
        if (locationDTO != null) {
            return ResponseEntity.ok(locationDTO);
        } else {
            logger.error("Location with id {} not found", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
