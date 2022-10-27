package hr.luminumict.pdfgenerator.util;

import hr.luminumict.pdfgenerator.model.dto.Employee;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VacationDaysPdfGenerate {

    public static JasperPrint generatePdf(Employee employee, int numberOfDays, Date startDate, Date endDate) throws JRException, FileNotFoundException {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", employee.getFirstName() + " " + employee.getLastName());
        parameters.put("numberOfDays", numberOfDays);
        parameters.put("startDate", startDate);
        parameters.put("endDate", endDate);
        InputStream input = new FileInputStream(VacationDaysPdfGenerate.class.getClassLoader().getResource("vacation-request-template.jrxml").getPath());

        JasperDesign jasperDesign = JRXmlLoader.load(input);

        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
    }
}
