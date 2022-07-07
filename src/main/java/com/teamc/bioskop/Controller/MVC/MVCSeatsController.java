package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Model.Films;
import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Service.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MVCSeatsController {

    private final SeatsService seatService;



    @GetMapping("/getseats")
    public String showSeats(Model model){
        model.addAttribute("seats", seatService.findAllseats());
        return "seats";
    }

    @GetMapping("/add-seats")
    public String formSeats(Model model) {
        Seats seat = new Seats();
        model.addAttribute("seats", seat);

        return "add-seats";
    }

    @PostMapping("/added-seats")
    public String newSeats(@ModelAttribute("seats") Seats seats) {
        this.seatService.createseat(seats);
        return "success-added-seats";
    }

    /*
    Update Seats Session
     */
    @GetMapping("/update/seat/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Seats seat = this.seatService.getReferenceById(id);
        model.addAttribute("seat", seat);
        return "update-seat";
    }

    @PostMapping("/update/seat/{id}")
    public String updateById(@PathVariable("id") Long id,
                             @ModelAttribute("seats") Seats seats) {
        seats.setSeatId(id);
        this.seatService.updateseat(seats, id);
        return "success-updated-seats";
    }

    @GetMapping("/delete/seat/{id}")
    public String deleteSeat(@PathVariable("id") long id, Model model) {
        Seats seats = seatService.findbyid(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        seatService.deleteseat(id);
        return "redirect:/getseats";
    }
}