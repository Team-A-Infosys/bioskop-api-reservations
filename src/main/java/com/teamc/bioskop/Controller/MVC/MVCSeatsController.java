package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Model.Seats;
import com.teamc.bioskop.Service.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    /*
    Update Seats Session
     */
    @GetMapping("/update/seat/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Seats seat = this.seatService.getReferenceById(id);
        model.addAttribute("seat", seat);
        return "update-seat";
    }

    /*
    @PostMapping("/update/{id}")
    public String updateSeat(@PathVariable("id") long id, @Valid Seats seats, BindingResult result, Model model){
        if(result.hasErrors()){
            seats.setSeatId(id);
            return "update-seat";
        }

        seatService.createseat(seats);
        return "redirect:/index";
    }
     */

    @PostMapping("/update/seat/{id}")
    public String updateById(@PathVariable("id") Long id,
                             @ModelAttribute("seats") Seats seats){
        seats.setSeatId(id);
        this.seatService.updateseat(seats,id);
        return "success-updated-seats";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model){
        Seats seats = seatService.findbyid(id).orElseThrow(() -> new IllegalArgumentException("Invalid user id : " + id));
        seatService.deleteseat(id);
        return "redirect:/index";
    }
}
