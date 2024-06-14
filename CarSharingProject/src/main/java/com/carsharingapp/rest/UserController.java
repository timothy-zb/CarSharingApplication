package com.carsharingapp.rest;

import com.carsharingapp.domain.Role;
import com.carsharingapp.domain.User;
import com.carsharingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestParam String firstName,
                                    @RequestParam String lastName,
                                    @RequestParam Role role,
                                    @RequestParam String phoneNumber,
                                    @RequestParam MultipartFile displayPicture,
                                    @RequestParam(required = false) String number,
                                    @RequestParam(required = false) Integer seats) {
        logger.info("Received signup request for user: {} {}", firstName, lastName);
        try {
            User user = userService.signUp(firstName, lastName, role, phoneNumber, displayPicture, number, seats);
            logger.info("User created successfully: {}", user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IOException e) {
            logger.error("Error uploading display picture", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading display picture");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid role provided", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role provided");
        }
    }
}
