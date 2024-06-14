package com.carsharingapp.service;

import com.carsharingapp.domain.Driver;
import com.carsharingapp.domain.Passenger;
import com.carsharingapp.domain.Role;
import com.carsharingapp.domain.User;
import com.carsharingapp.repository.DriverRepository;
import com.carsharingapp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserService {

    private final PassengerRepository passengerRepository;
    private final DriverRepository driverRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    public UserService(PassengerRepository passengerRepository, DriverRepository driverRepository) {
        this.passengerRepository = passengerRepository;
        this.driverRepository = driverRepository;
    }

    public User signUp(String firstName, String lastName, Role role, String phoneNumber, MultipartFile displayPicture, String number, Integer seats) throws IOException {
        String fileName = saveDisplayPicture(displayPicture);

        if (role == Role.CAB_DRIVER) {
            Driver driver = new Driver();
            driver.setFirstName(firstName);
            driver.setLastName(lastName);
            driver.setPhoneNumber(phoneNumber);
            driver.setRole(role);
            driver.setDisplayPicture(fileName);
            driver.setNumber(number);
            driver.setSeats(seats);
            return driverRepository.save(driver);
        } else {
            Passenger passenger = new Passenger();
            passenger.setFirstName(firstName);
            passenger.setLastName(lastName);
            passenger.setRole(role);
            passenger.setPhoneNumber(phoneNumber);
            passenger.setDisplayPicture(fileName);
            return passengerRepository.save(passenger);
        }
    }

    private String saveDisplayPicture(MultipartFile displayPicture) throws IOException {
        // Ensure the upload directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Create a unique file name to avoid collisions
        String originalFileName = displayPicture.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        Path path = uploadPath.resolve(uniqueFileName);
        Files.write(path, displayPicture.getBytes());

        return uniqueFileName;
    }
}
