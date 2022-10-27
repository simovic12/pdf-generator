package hr.luminumict.pdfgenerator.controller;

import hr.luminumict.pdfgenerator.exception.EmployeeNotFoundException;
import hr.luminumict.pdfgenerator.model.request.VacationDaysRequest;
import hr.luminumict.pdfgenerator.service.VacationDaysService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping(path = "api/vacations")
public class VacationDaysController {

    private final VacationDaysService service;

    @Autowired
    public VacationDaysController(VacationDaysService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity requestForVacation(@RequestBody VacationDaysRequest request) {
        if (request == null || request.getEmployeeId() == null || request.getStartDate() == null || request.getEndDate() == null || request.getNumberOfDays() < 1) {
            return ResponseEntity.badRequest().body("Bad Request");
        }
        if (request.getStartDate().after(request.getEndDate())){
            return ResponseEntity.badRequest().body("Bad Date Request");
        }

        try {
            JasperPrint jasperPrint = service.generatePdf(request);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-Disposition")
                    .body(out.toByteArray());
        } catch (JRException | FileNotFoundException e) {
            return ResponseEntity.internalServerError().body(e);
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}