package com.teamc.bioskop.Controller.mvc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    private final MVCFilmController mvcFilmController;

    @GetMapping("/")
    public String index(){
        return "index";
    }
}