package com.fuelstation.vehicle_service_fuel_station.bookingmanagement.service;

import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.Booking;
import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.repository.BookingRepository;
import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.repository.BookingSpecifications;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    // ===== Stat cards =====
    public long countPending() {
        return bookingRepository.countByStatusIgnoreCase("Pending");
    }

    public long countProgress() {
        return bookingRepository.countByStatusIgnoreCase("In Progress");
    }

    public long countCompleted() {
        return bookingRepository.countByStatusIgnoreCase("Completed");
    }

    public long countCancelled() {
        return bookingRepository.countByStatusIgnoreCase("Cancelled");
    }

    // ===== Search + status filter + pagination =====
    public Page<Booking> findAll(String q, String status, int page, int size) {
        Specification<Booking> spec = Specification.where(BookingSpecifications.search(q))
                .and(BookingSpecifications.hasStatus(status));
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "bookingDate"));
        return bookingRepository.findAll(spec, pageable);
    }

    // ===== CRUD (kept) =====
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "bookingDate"));
    }

    public void saveBooking(Booking booking) {
        if (booking.getStatus() == null || booking.getStatus().isBlank())
            booking.setStatus("Pending");
        if (booking.getBookingDate() == null)
            booking.setBookingDate(LocalDateTime.now());
        bookingRepository.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
