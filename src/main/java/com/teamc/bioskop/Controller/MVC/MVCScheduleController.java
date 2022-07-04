package com.teamc.bioskop.Controller.MVC;

import com.teamc.bioskop.Controller.ScheduleController;
import com.teamc.bioskop.DTO.ScheduleRequestDTO;
import com.teamc.bioskop.Model.Schedule;
import com.teamc.bioskop.Service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class MVCScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/allSchedules")
    public String showAllSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAll());

        return "schedule";
    }

    @GetMapping("/add-schedule")
    public String showForm(Model model){
        Schedule schedule = new Schedule();

        model.addAttribute("schedule", schedule);

        return "add-schedule";
    }

    @PostMapping("/add-schedule")
    public String submitSchedule(@ModelAttribute("schedule")ScheduleRequestDTO requestDTO){
        ScheduleController scheduleController = new ScheduleController(scheduleService);

        scheduleController.createScheduleDTO(requestDTO);

        return "success";
    }
}