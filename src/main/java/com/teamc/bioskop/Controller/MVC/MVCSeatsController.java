package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Service.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MVCSeatsController {

    private final SeatsService seatService;

    @GetMapping("/getseats")
    public String showSeatsList(Model model) {
        model.addAttribute("seats", seatService.findAllseats());
        return "seats";
    }

    @GetMapping("/add-seats")
    public String formSeats(Model model){
        Seats seat = new Seats();
        model.addAttribute("seats",seat);

        return "add-seats";
    }

    @PostMapping("/added-seats")
    public String newSeats(@ModelAttribute("seats") Seats seats){
        this.seatService.createseat(seats);
        return "success-added-seats";
    }
}
