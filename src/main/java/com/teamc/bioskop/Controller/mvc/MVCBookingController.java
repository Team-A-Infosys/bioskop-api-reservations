package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @PostMapping("/update/{id}")
}
