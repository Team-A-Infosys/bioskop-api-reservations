package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Controller.FilmsController;
import com.teamc.bioskop.Model.Films;
import com.teamc.bioskop.Model.StatusFilms;
import com.teamc.bioskop.Service.FilmsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class MVCFilmController {

    private final FilmsService filmsService;
    @GetMapping("/films")
    public String showFilms(Model model){
      model.addAttribute("films", filmsService.findAllFilms());
      return "films";
    }

    @GetMapping("films-status")
    public String showFilmByStatus(Model model, @RequestParam(value = "isPlaying", required = false) StatusFilms statusFilms ){
        model.addAttribute("films", filmsService.getByIsPlaying(statusFilms));

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
        return "redirect:/films";
    }
    @GetMapping("/edit-film/{id}")
    public String showUpdatedForm(@PathVariable("id") Long id, Model model){
        Films films = this.filmsService.getReferenceById(id);
        model.addAttribute("film", films);
        return "edit-film";
    }
    @PostMapping("/update-film/{id}")
    public String updateFilm(@PathVariable("id") Long id, @ModelAttribute("film") Films films){
        films.setFilmId(id);
        this.filmsService.createFilm(films);
        return "success";
    }
    @GetMapping("/delete-film/{id}")
    public String deleteFilm(@PathVariable("id") Long id){
        this.filmsService.deleteFilmById(id);

        return "redirect:/films";
    }




}