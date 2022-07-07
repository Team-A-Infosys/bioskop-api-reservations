package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Exception.ResourceNotFoundException;
import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Model.Schedule;
import com.teamc.bioskop.Model.User;
import com.teamc.bioskop.Service.BookingService;
import com.teamc.bioskop.Service.ScheduleService;
import com.teamc.bioskop.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.Id;
import java.util.List;


@AllArgsConstructor
@Controller


public class MVCBookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final ScheduleService scheduleService;


    @GetMapping("/bookings")
    public String bookingList(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        return "bookings";
    }

    @GetMapping("/bookings/{id}")
    public String showBookings(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookings", bookingService.getBookingById(id));
        return "bookings";
    }

    @GetMapping("/add-bookings")
    public String addBooking(Model model) {
        Booking bookings = new Booking();
        model.addAttribute("bookings", bookings);
        return "add-booking";
    }

    @PostMapping("/tambah-bookings")
    public String submitNewBooking(@ModelAttribute("bookings") Booking booking) {
        this.bookingService.createBooking(booking);
        return "success";
    }

    @GetMapping("/update-bookings/{id}")
    public String showEditBooking(@PathVariable("id") Long id, Model model) {
        List<User> users = userService.getAll();
        List<Schedule> schedules = scheduleService.getAll();
        Booking bookings = this.bookingService.getReferenceById(id);
        model.addAttribute("users", users);
        model.addAttribute("schedules", schedules);
        model.addAttribute("editBookings", new Booking());
        model.addAttribute("bookings", bookings);
        return "update-booking";
    }

    @PutMapping("/updated-bookings/{id}")
    public String editBooking(@PathVariable("id") Long id, @ModelAttribute Booking booking) {
        booking.setBookingId(id);
        bookingService.updateBooking(booking);
        return "redirect:/update-bookings/success";
    }

    @GetMapping("/update-bookings/success")
    public String deleteSuccess() {
        return "success-updated-booking";
    }

    @PostMapping("/updated-bookings/{id}")
    public String updateById(@PathVariable("id") Long id,
                             @ModelAttribute("bookings") Booking bookings) {
        bookings.setBookingId(id);
        this.bookingService.updateBooking(bookings);
        return "success-updated-booking";
    }

    @GetMapping("/delete-bookings/success")
    public String deleteById(Model model) {
        return "success-delete";
    }

    @DeleteMapping("/deleted/bookings{id}")
    public String deleteById(Model model, @PathVariable("id") Long id) throws ResourceNotFoundException {
        bookingService.deleteSBookingById(id);
        return "redirect:/delete-bookings/success";
    }
}