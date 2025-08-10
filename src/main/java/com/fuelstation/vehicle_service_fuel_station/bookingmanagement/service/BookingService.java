package com.fuelstation.vehicle_service_fuel_station.bookingmanagement.service;

import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.Booking;
import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }

    public Booking save(Booking booking) {
        return repository.save(booking);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Booking findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
