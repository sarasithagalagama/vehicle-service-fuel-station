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

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public String listBookings(Model model) {
        model.addAttribute("bookings", service.findAll());
        return "booking_list";
    }

    @GetMapping("/new")
    public String newBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "booking_form";
    }

    @PostMapping
    public String saveBooking(@ModelAttribute Booking booking) {
        booking.setBookingDate(LocalDateTime.now());
        service.save(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/bookings";
    }
}
