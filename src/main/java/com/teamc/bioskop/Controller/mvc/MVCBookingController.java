package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Exception.ResourceNotFoundException;
import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        this.bookingService.createBooking(booking);
        return"success";
    }

    @GetMapping("/update/bookings/{id}")
    public String showEditBooking (@PathVariable("id") Long id, Model model){
        Booking bookings = this.bookingService.getReferenceById(id);
        model.addAttribute("bookings", bookings);
        return "update-booking";
    }

    @PostMapping("/updated/bookings/{id}")
    public String updateById(@PathVariable("id") Long id,
                             @ModelAttribute("bookings") Booking bookings){
        bookings.setBookingId(id);
        this.bookingService.updateBooking(bookings);
        return "success-updated-booking";
    }

    @DeleteMapping("/delete/bookings{id}")
    public String deleteById(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException{
        bookingService.deleteSBookingById(id);
        return "success-delete";
    }
}