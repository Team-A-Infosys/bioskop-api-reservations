package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.DTO.FilmsRequestDTO;
import com.teamc.bioskop.Model.Films;
import com.teamc.bioskop.Service.BookingService;
import com.teamc.bioskop.Service.FilmsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MVCFilmController {

    private final FilmsService filmsService;

    @GetMapping("/films")
    public String showFilms(Model model){
      model.addAttribute("films", filmsService.findAllFilms());
      return "films";
    }


    @GetMapping("/add-film")
    public String formFilm(Model model){
        Films films = new Films();
        model.addAttribute("film", films);

        return "add-film";
    }

    @PostMapping("/tambah-film")
    public String submitFilm(@ModelAttribute("film") Films films){
        this.filmsService.createFilm(films);
        return "success";
    }



}