package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Service.SeatsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MVCSeatsController {

    private final SeatsServiceImpl seatServiceImp;

    @GetMapping("/getseats")
    public String showSeatsList(Model model) {
        model.addAttribute("seats", seatServiceImp.findAllseats());
        return "seats";
    }
}
