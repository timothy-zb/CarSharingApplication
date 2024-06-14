package com.carsharingapp.rest;

import com.carsharingapp.domain.Location;
import com.carsharingapp.domain.RideRequest;
import com.carsharingapp.service.LocationService;
import com.carsharingapp.service.RideRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ride-requests")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<RideRequest> findAll() {
        return rideRequestService.findAll();
    }

    @GetMapping("/{id}")
    public RideRequest findById(@PathVariable Long id) {
        return rideRequestService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RideRequest save(@RequestBody RideRequest rideRequest) {
        return rideRequestService.save(rideRequest);
    }

    @GetMapping("/search")
    public List<RideRequest> search(
            @RequestParam("from") Long fromId,
            @RequestParam("to") Long toId,
            @RequestParam("departureTime") String departureTime) {
        Location from = locationService.findById(fromId);
        Location to = locationService.findById(toId);
        ZonedDateTime departure = ZonedDateTime.parse(departureTime);
        return rideRequestService.findByFromAndToAndDepartureTime(from, to, departure);
    }

    @PostMapping("/{id}/accept")
    @ResponseStatus(HttpStatus.OK)
    public RideRequest acceptRideRequest(@PathVariable Long id) {
        RideRequest rideRequest = rideRequestService.findById(id);
        rideRequest.setAccepted(true);
        return rideRequestService.save(rideRequest);
    }
}
