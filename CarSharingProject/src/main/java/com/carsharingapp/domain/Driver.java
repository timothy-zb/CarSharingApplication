package com.carsharingapp.domain;

import javax.persistence.Entity;

@Entity
public class Driver extends User {

    private String number;
    private int seats;

    // Getters and setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", Licence Plate='" + getNumber() + '\'' +
                ", Seats=" + getSeats() +
                '}';
    }
}
