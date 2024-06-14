package com.carsharingapp.rest.DTO;

public class LocationDTO {

    private LocationDetails location;
    private LocationDetails destination;

    public LocationDetails getLocation() {
        return location;
    }

    public void setLocation(LocationDetails location) {
        this.location = location;
    }

    public LocationDetails getDestination() {
        return destination;
    }

    public void setDestination(LocationDetails destination) {
        this.destination = destination;
    }

    public static class LocationDetails {
        private String address;
        private double latitude;
        private double longitude;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
