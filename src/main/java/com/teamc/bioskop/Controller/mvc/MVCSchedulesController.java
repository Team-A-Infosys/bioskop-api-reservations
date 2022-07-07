package com.teamc.bioskop.Controller.mvc;
import com.teamc.bioskop.Model.Schedule;
import com.teamc.bioskop.Service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class MVCSchedulesController {
private ScheduleService scheduleService;

    @GetMapping("/schedule/AllSchedule")
    public String showSchedulesList(Model model) {
        model.addAttribute("schedules", scheduleService.getAll());
        return "Schedules";
    }

    @GetMapping("/edit/schedule/{id}")
    public String showSchedule(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("schedule", scheduleService.getScheduleById(id));
        return "Schedules";
    }

    @GetMapping("/add-schedule")
    public String addSchedule(Model model) {
        Schedule schedules = new Schedule();
        model.addAttribute("schedule", schedules);
        return "add-schedule";
    }

    @PostMapping("/tambah-schedule")
    public String submitSchedule(@ModelAttribute("schedule")Schedule schedules) {
        this.scheduleService.createSchedule(schedules);
        return "redirect:/schedule/AllSchedule";
    }
    @GetMapping("/update/schedule/{id}")
    public String showEditSchedule(Model model, @PathVariable ("id")Integer id) {
        Schedule schedules = this.scheduleService.getReferenceById(id);
        model.addAttribute("schedule", schedules);
        return "update-schedule";
    }
        @PostMapping("/updated/schedule/{id}")
        public String updateById(@PathVariable("id") Integer id,
                                 @ModelAttribute("schedule")Schedule schedules) {
        schedules.setScheduleId(id);
            scheduleService.createSchedule(schedules);
            return "redirect:/schedule/AllSchedule";
    }
    @GetMapping("/delete/schedule/{id}")
    public String deleteSchedule(@PathVariable("id") Integer id) {
        this.scheduleService.deleteScheduleById(id);
        return "redirect:/schedule/AllSchedule";
    }
}
