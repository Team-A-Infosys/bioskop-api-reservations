package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Id;

@AllArgsConstructor
@Controller

public class MVCBookingController {

    private final BookingService bookingService;


    @GetMapping("/bookings")
    public String bookingList( Model model){
        model.addAttribute("bookings", bookingService.getAll());
        return "bookings";
    }

    @GetMapping("/bookings/{id}")
    public String showBookings(@PathVariable("id") Long id, Model model){
        model.addAttribute("bookings", bookingService.getBookingById(id));
        return "bookings";
    }

    @GetMapping("/add-bookings")
    public String addBooking ( Model model){
        Booking bookings = new Booking();
        model.addAttribute("bookings", bookings);
        return "add-booking";
    }

    @PostMapping("/tambah-bookings")
    public String submitNewBooking(@ModelAttribute("bookings") Booking booking){
        return"success";
    }
}
