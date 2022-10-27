package hr.luminumict.pdfgenerator.service;

import hr.luminumict.pdfgenerator.util.VacationDaysPdfGenerate;
import hr.luminumict.pdfgenerator.exception.EmployeeNotFoundException;
import hr.luminumict.pdfgenerator.model.dto.Employee;
import hr.luminumict.pdfgenerator.model.request.VacationDaysRequest;
import hr.luminumict.pdfgenerator.model.repository.EmployeeRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Optional;

@Service
public class VacationDaysService {

    private final EmployeeRepository repository;

    @Autowired
    public VacationDaysService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public JasperPrint generatePdf(VacationDaysRequest request) throws JRException, FileNotFoundException, EmployeeNotFoundException {
        Optional<Employee> employee = repository.findById(request.getEmployeeId());
        if (employee.isPresent()) {
            JasperPrint response = VacationDaysPdfGenerate.generatePdf(employee.get(), request.getNumberOfDays(), request.getStartDate(), request.getEndDate());
            return response;
        }
        throw new EmployeeNotFoundException(String.format("Employee with provided id: %s not found!", request.getEmployeeId()));
    }


}