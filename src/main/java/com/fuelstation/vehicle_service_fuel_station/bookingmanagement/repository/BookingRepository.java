package com.fuelstation.vehicle_service_fuel_station.bookingmanagement.repository;

import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
