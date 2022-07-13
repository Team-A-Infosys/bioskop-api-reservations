package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Exception.ResourceNotFoundException;
import com.teamc.bioskop.Model.Schedule;
import com.teamc.bioskop.Service.ScheduleServiceImpl;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@AllArgsConstructor
@Controller
public class MVCSchedulesController {
    private ScheduleServiceImpl scheduleService;

    private HttpServletResponse response;

    @GetMapping("/schedule/AllSchedule")
    public String showSchedulesList(Model model) {
        return paginatedSchedule(1, model);
    }

    @GetMapping("/schedule/AllSchedule/{page}")
    public String paginatedSchedule(@PathVariable(value="page") int pageNumber, Model model) {
        int pageSize = 5;
        Page<Schedule> schedulePage = this.scheduleService.findPaginated(pageNumber, pageSize);
        List<Schedule> scheduleList = schedulePage.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", schedulePage.getTotalPages());
        model.addAttribute("totalItems", schedulePage.getTotalElements());
        model.addAttribute("Schedules",scheduleList);

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
    public String submitSchedule(@ModelAttribute("schedule") Schedule schedules) {
        this.scheduleService.createSchedule(schedules);
        return "redirect:/schedule/AllSchedule";
    }

    @GetMapping("/update/schedule/{id}")
    public String showEditSchedule(Model model, @PathVariable("id") Integer id) {
        Schedule schedules = this.scheduleService.getReferenceById(id);
        model.addAttribute("schedule", schedules);
        return "update-schedule";
    }

//    @PostMapping("/updated/schedule/{id}")
//    public String updateById(@PathVariable("id") Integer id,
//                             @ModelAttribute("schedule") Schedule schedules) {
//        schedules.setScheduleId(id);
//        scheduleService.createSchedule(schedules);
//        return "redirect:/schedule/AllSchedule";
//    }

//    @PutMapping("/updated/schedule/{id}")
//    public String editSchedule(@PathVariable("id") Integer id, @ModelAttribute Schedule schedules) {
//        schedules.setScheduleId(id);
//        scheduleService.updateSchedule(schedules);
//        return "redirect:/Schedules";
//    }

    @GetMapping("/update/schedule/success")
    public String deleteSuccess() {
        return "success-updated-schedule";
    }

    @PostMapping("updated/schedule/{id}")
    public String updateById(@PathVariable("id") Integer id, @ModelAttribute("schedule") Schedule schedules, RedirectAttributes redirectAttributes){
        schedules.setScheduleId(id);
        redirectAttributes.addFlashAttribute("success","success edit schedule");
        return "redirect:/schedule/AllSchedule";

    }
    @GetMapping("/deleted/schedule/{id}")
    public String deleteScheduleById(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes)throws ResourceNotFoundException {
        scheduleService.deleteScheduleById(id);
        redirectAttributes.addFlashAttribute("deleted", "success delete schedule");
        return "redirect:/schedule/AllSchedule";
    }

    @GetMapping("/cetak-schedule")
    public void printReport() throws Exception{
        response.setContentType(("application/pdf"));
        response.setHeader("Content-Disposition", "attachment; filename=\"Schedule_list.pdf\"");
        JasperPrint jasperPrint = this.scheduleService.generateJasperPrint();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }
//    @GetMapping("/delete/schedule/success")
//    public String deleteScheduleById(Model model) {
//        return "success-delete-schedule";
//    }
}