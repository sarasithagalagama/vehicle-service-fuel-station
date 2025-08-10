package com.fuelstation.vehicle_service_fuel_station.bookingmanagement.controller;

import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.model.Booking;
import com.fuelstation.vehicle_service_fuel_station.bookingmanagement.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String listBookings(Model model) {
        model.addAttribute("bookings", bookingService.findAll());
        return "booking_list"; // Thymeleaf file name
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form";
    }

    @PostMapping
    public String saveBooking(@ModelAttribute("booking") Booking booking) {
        if (booking.getId() != null) {
            Booking existing = bookingService.findById(booking.getId());
            if (existing != null) {
                booking.setBookingDate(existing.getBookingDate());
            }
        } else {
            booking.setBookingDate(LocalDateTime.now());
        }
        bookingService.save(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findById(id);
        if (booking == null) {
            model.addAttribute("errorMessage", "Booking not found.");
            model.addAttribute("bookings", bookingService.findAll());
            return "booking_list";
        }
        model.addAttribute("booking", booking);
        return "booking_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return "redirect:/bookings";
    }
}
