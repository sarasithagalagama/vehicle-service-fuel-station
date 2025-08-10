package com.fuelstation.vehicle_service_fuel_station.bookingmanagement.controller;

import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.Booking;
import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.BookingStatus;
import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.service.BookingService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // LIST + search + filter + pagination + stat cards
    @GetMapping
    public String listBookings(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(required = false) String status, // "Pending", "In Progress", etc.
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        BookingStatus statusEnum = null;
        if (status != null && !status.isBlank()) {
            statusEnum = BookingStatus.valueOf(status.toUpperCase().replace(' ', '_'));
        }

        Page<Booking> pg = bookingService.findAll(q, statusEnum, page, size);

        // stat cards
        model.addAttribute("pendingCount", bookingService.countByStatus(BookingStatus.PENDING));
        model.addAttribute("inProgressCount", bookingService.countByStatus(BookingStatus.IN_PROGRESS));
        model.addAttribute("completedCount", bookingService.countByStatus(BookingStatus.COMPLETED));
        model.addAttribute("cancelledCount", bookingService.countByStatus(BookingStatus.CANCELLED));

        // table + paging + filters
        model.addAttribute("bookings", pg.getContent());
        model.addAttribute("page", pg.getNumber());
        model.addAttribute("totalPages", pg.getTotalPages());
        model.addAttribute("q", q);
        model.addAttribute("status", status); // keep original text for the dropdown

        return "booking_list"; // Thymeleaf page you already have
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form";
    }

    @PostMapping
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(id));
        return "booking_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }
}
