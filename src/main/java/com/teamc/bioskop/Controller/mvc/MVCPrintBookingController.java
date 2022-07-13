package com.teamc.bioskop.Controller.mvc;

import com.teamc.bioskop.Service.ReportPDFBookingService;
import com.teamc.bioskop.Service.ReportPDFService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class MVCPrintBookingController {

    private ReportPDFBookingService reportPDFBookingService;

    private HttpServletResponse response;

    @GetMapping("/cetak")
    public void printReport() throws Exception{
        response.setContentType(("application/pdf"));
        response.setHeader("Content-Disposition", "attachment; filename=\"booking_list.pdf\"");
        JasperPrint jasperPrint = this.reportPDFBookingService.generateJasperPrint();
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();

    }

}

