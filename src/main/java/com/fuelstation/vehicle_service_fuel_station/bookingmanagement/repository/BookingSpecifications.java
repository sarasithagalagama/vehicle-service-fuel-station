package com.fuelstation.vehicle_service_fuel_station.bookingmanagement.repository;

import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.Booking;
import org.springframework.data.jpa.domain.Specification;

public final class BookingSpecifications {
    private BookingSpecifications() {
    }

    public static Specification<Booking> search(String q) {
        return (root, cq, cb) -> {
            if (q == null || q.isBlank())
                return cb.conjunction();
            String like = "%" + q.trim().toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("customerName")), like),
                    cb.like(cb.lower(root.get("vehicleNumber")), like),
                    cb.like(cb.lower(root.get("serviceType")), like));
        };
    }

    public static Specification<Booking> hasStatus(String status) {
        return (root, cq, cb) -> (status == null || status.isBlank())
                ? cb.conjunction()
                : cb.equal(cb.lower(root.get("status")), status.toLowerCase());
    }
}
