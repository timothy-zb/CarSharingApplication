package com.carsharingapp.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class RideRequest {

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Ride ride;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Location from;

    @ManyToOne
    private Location to;

    private int seats;
    private ZonedDateTime departureTime;
    private boolean accepted;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RideRequest{" +
                "id=" + id +
                ", ride=" + ride +
                ", passenger=" + passenger +
                ", driver=" + driver +
                ", from=" + from +
                ", to=" + to +
                ", seats=" + seats +
                ", departureTime=" + departureTime +
                ", accepted=" + accepted +
                ", status=" + status +
                '}';
    }
}
