package com.carsharingapp.service;

import com.carsharingapp.domain.Driver;
import com.carsharingapp.exception.DriverNotFoundException;
import com.carsharingapp.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository repository;

    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    public Driver findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DriverNotFoundException("Driver not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new DriverNotFoundException("Driver not found with id: " + id);
        }
    }
}
