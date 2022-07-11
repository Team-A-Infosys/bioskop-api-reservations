package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Model.Booking;
import com.teamc.bioskop.Model.Films;
import com.teamc.bioskop.Service.FilmsService;
import com.teamc.bioskop.Service.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class MVCMainController {
    private final FilmsService filmsService;
    private final SeatsService seatService;

    @GetMapping("/")
    public String showIndex(Model model, String studioA, String studioB, String studioC, String studioD) {
        studioA = "Studio A";
        studioB = "Studio B";
        studioC = "Studio C";
        studioD = "Studio D";
        model.addAttribute("seatsA", seatService.getSeatsByStudioName(studioA));
        model.addAttribute("seatsB", seatService.getSeatsByStudioName(studioB));
        model.addAttribute("seatsC", seatService.getSeatsByStudioName(studioC));
        model.addAttribute("seatsD", seatService.getSeatsByStudioName(studioD));

        Films films = new Films();
        Booking bookings = new Booking();

        model.addAttribute("film", films);
        model.addAttribute("bookings", bookings);
        model.addAttribute("seats", seatService.findAllseats());
        return paginatedFilm(1, model,studioA,studioB,studioC,studioD);
    }

    @GetMapping("/films-page-{page}")
    public String paginatedFilm(@PathVariable(value = "page") int pageNo, Model model, String studioA, String studioB, String studioC, String studioD) {
        int pageSize = 8;

        Page<Films> filmsPage = this.filmsService.findPaginated(pageNo, pageSize);
        List<Films> filmsList = filmsPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", filmsPage.getTotalPages());
        model.addAttribute("totalItems", filmsPage.getTotalElements());
        model.addAttribute("listFilms", filmsList);
        Booking bookings = new Booking();
        model.addAttribute("bookings", bookings);
        studioA = "Studio A";
        studioB = "Studio B";
        studioC = "Studio C";
        studioD = "Studio D";
        model.addAttribute("seatsA", seatService.getSeatsByStudioName(studioA));
        model.addAttribute("seatsB", seatService.getSeatsByStudioName(studioB));
        model.addAttribute("seatsC", seatService.getSeatsByStudioName(studioC));
        model.addAttribute("seatsD", seatService.getSeatsByStudioName(studioD));


        return "index";
    }
}
