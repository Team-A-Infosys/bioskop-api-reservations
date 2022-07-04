package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class MVCSchedulesController {
private ScheduleService scheduleService;

    @GetMapping("/schedule/AllSchedule")
    public String showSchedulesList(Model model) {
        model.addAttribute("schedules", scheduleService.getAll());
        return "Schedules";
    }
}
