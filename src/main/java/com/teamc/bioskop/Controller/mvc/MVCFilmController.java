package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Model.Films;
import com.teamc.bioskop.Model.StatusFilms;
import com.teamc.bioskop.Service.FilmsService;
import com.teamc.bioskop.Service.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class MVCFilmController {

    private final FilmsService filmsService;
    @GetMapping("/management/films")
    public String showIndex(Model model) {

        return paginatedFilm(1, model);
    }
    @GetMapping("/management/films/{page}")
    public String paginatedFilm(@PathVariable(value = "page") int pageNo, Model model) {
        int pageSize = 5;

        Page<Films> filmsPage = this.filmsService.findPaginated(pageNo, pageSize);
        List<Films> filmsList = filmsPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", filmsPage.getTotalPages());
        model.addAttribute("totalItems", filmsPage.getTotalElements());
        model.addAttribute("listFilms", filmsList);

        return "films";
    }

    @GetMapping("/management/films-status")
    public String showFilmsByStatus(@RequestParam(value = "isPlaying", required = false) StatusFilms statusFilms, Model model) {
        return paginatedFilmByStatus(1, model, statusFilms);
    }

    @GetMapping("/management/films-status/{pageStatus}")
    public String paginatedFilmByStatus(@PathVariable(value = "pageStatus") int pageNo, Model model, @RequestParam(value = "isPlaying", required = false) StatusFilms statusFilms) {
        int pageSize = 5;

        Page<Films> filmsPage = this.filmsService.findPaginatedByStatus(statusFilms, pageNo, pageSize);
        List<Films> filmsList = filmsPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", filmsPage.getTotalPages());
        model.addAttribute("totalItems", filmsPage.getTotalElements());
        model.addAttribute("listFilms", filmsList);

        return "films";
    }

    @GetMapping("/management/add-film")
    public String addFilm(Model model) {
        Films films = new Films();
        model.addAttribute("film", films);
        return "add-film";
    }

    @PostMapping("/management/tambah-film")
    public String submitFilm(@ModelAttribute("film") Films films) {
        this.filmsService.createFilm(films);
        return "redirect:/management/films";
    }

    @GetMapping("/management/edit-film/{id}")
    public String showUpdatedForm(@PathVariable("id") Long id, Model model) {
        Films films = this.filmsService.getReferenceById(id);
        model.addAttribute("film", films);
        return "edit-film";
    }

    @PostMapping("/management/edit-film/{id}")
    public String updateFilm(@PathVariable("id") Long id, @ModelAttribute("film") Films films) {
        films.setFilmId(id);
        this.filmsService.createFilm(films);
        return "redirect:/management/films";
    }

    @GetMapping("/management/delete-film/{id}")
    public String deleteFilm(@PathVariable("id") Long id) {
        this.filmsService.deleteFilmById(id);

        return "redirect:/management/films";
    }

}