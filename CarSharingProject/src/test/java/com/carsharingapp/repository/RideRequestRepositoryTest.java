package com.carsharingapp.repository;

import com.carsharingapp.domain.Ride;
import com.carsharingapp.domain.RideRequest;
import com.carsharingapp.domain.Passenger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RideRequestRepositoryTest {
    private static final Logger log = LoggerFactory.getLogger(RideRequestRepositoryTest.class);

    @Autowired
    private RideRequestRepository repository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Sql({"/location.sql", "/passenger.sql", "/ride.sql"})
    @Test
    public void testSaveRideRequest() {
        RideRequest request = new RideRequest();
        request.setStatus(RideRequest.Status.ACCEPTED);

        Optional<Ride> rideOptional = rideRepository.findById(1L);
        Assert.assertTrue("Ride should be present in the repository", rideOptional.isPresent());
        Ride ride = rideOptional.get();

        Optional<Passenger> passengerOptional = passengerRepository.findById(1L);
        Assert.assertTrue("Passenger should be present in the repository", passengerOptional.isPresent());
        Passenger passenger = passengerOptional.get();

        request.setRide(ride);
        request.setPassenger(passenger);

        repository.saveAndFlush(request);

        Assert.assertNotNull("RideRequest ID should not be null after saving", request.getId());
        Assert.assertEquals("RideRequest status should be ACCEPTED", RideRequest.Status.ACCEPTED, request.getStatus());
    }
}
