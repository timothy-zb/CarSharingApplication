package com.carsharingapp.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location from;

    @ManyToOne
    private Location to;

    private ZonedDateTime when;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    private List<Passenger> passengers;

    @OneToMany(mappedBy = "ride")
    private List<RideRequest> requests;

    private String ridePassword;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ZonedDateTime getWhen() {
        return when;
    }

    public void setWhen(ZonedDateTime when) {
        this.when = when;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<RideRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<RideRequest> requests) {
        this.requests = requests;
    }

    public String getRidePassword() {
        return ridePassword;
    }

    public void setRidePassword(String ridePassword) {
        this.ridePassword = ridePassword;
    }

    public enum Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        CANCELED
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", from=" + from +
                ", to=" + to +
                ", when=" + when +
                ", status=" + status +
                ", ridePassword='" + ridePassword + '\'' +
                '}';
    }
}
