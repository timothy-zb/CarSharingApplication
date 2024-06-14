package com.carsharingapp.service;

import com.carsharingapp.domain.Location;
import com.carsharingapp.exception.LocationNotFoundException;
import com.carsharingapp.repository.LocationRepository;
import com.carsharingapp.rest.DTO.LocationDTO;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void saveLocation(LocationDTO locationDTO) {
        if (locationDTO == null || locationDTO.getLocation() == null || locationDTO.getDestination() == null) {
            throw new IllegalArgumentException("Location and destination details must be provided");
        }

        Location location = createLocation(locationDTO.getLocation());
        locationRepository.save(location);

        Location destination = createLocation(locationDTO.getDestination());
        locationRepository.save(destination);
    }

    private Location createLocation(LocationDTO.LocationDetails locationDetails) {
        Location location = new Location();
        location.setAddress(locationDetails.getAddress());
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());
        return location;
    }

    public Location findById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found with id: " + id));
    }

    public LocationDTO findLocationById(Long id) {
        Location location = findById(id);
        return convertToDTO(location);
    }

    private LocationDTO convertToDTO(Location location) {
        LocationDTO.LocationDetails locationDetails = new LocationDTO.LocationDetails();
        locationDetails.setAddress(location.getAddress());
        locationDetails.setLatitude(location.getLatitude());
        locationDetails.setLongitude(location.getLongitude());

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation(locationDetails);

        return locationDTO;
    }
}
