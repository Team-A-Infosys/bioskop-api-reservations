package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Exception.ResourceNotFoundException;
import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Model.Schedule;
import com.teamc.bioskop.Model.User;
import com.teamc.bioskop.Service.BookingService;
import com.teamc.bioskop.Service.ScheduleService;
import com.teamc.bioskop.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.persistence.Id;
import java.util.List;


@AllArgsConstructor
@Controller


public class MVCBookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final ScheduleService scheduleService;


    @GetMapping("/bookings")
    public String showBookings(Model model){
        return paginatedBookings(1, model);
    }

    @GetMapping("/bookings-{page}")
    public String paginatedBookings(@PathVariable(value="page") int pageNumber, Model model){
        int pageSize = 5;
        Page<Booking> bookingPage = this.bookingService.findPaginated(pageNumber,pageSize);
        List<Booking> bookingList = bookingPage.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages",bookingPage.getTotalPages());
        model.addAttribute("totalItems", bookingPage.getTotalElements());
        model.addAttribute("bookings",bookingList);

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
       return "redirect:/bookings";
    }

    @GetMapping("/update-bookings/success")
    public String deleteSuccess() {
        return "success-updated-booking";
    }

    @PostMapping("/updated-bookings/{id}")
    public String updateById(@PathVariable("id") Long id,
                             @ModelAttribute("bookings") Booking bookings, RedirectAttributes redirectAttributes) {
        bookings.setBookingId(id);
        this.bookingService.updateBooking(bookings);
        redirectAttributes.addFlashAttribute("success","success edit booking");
        return "redirect:/bookings";
    }

    @GetMapping("/delete-bookings/success")
    public String deleteById(Model model) {
        return "success-delete";
    }

    @GetMapping("/deleted/bookings{id}")
    public String deleteById(Model model, @PathVariable("id") Long id, RedirectAttributes redirectAttributes) throws ResourceNotFoundException {
        bookingService.deleteSBookingById(id);
        redirectAttributes.addFlashAttribute("deleted","success delete booking");
        return "redirect:/bookings";
    }
}