package com.teamc.bioskop.Controller.mvc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MVCManagementController {
    @GetMapping("/management")
    public String admin(){
        return "dashboard";
    }
}