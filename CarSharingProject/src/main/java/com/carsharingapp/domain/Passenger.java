package com.carsharingapp.domain;

import javax.persistence.Entity;

@Entity
public class Passenger extends User {

    // Additional fields specific to Passenger can go here

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }
}
