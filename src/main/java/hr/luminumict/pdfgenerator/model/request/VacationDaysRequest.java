package hr.luminumict.pdfgenerator.model.request;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationDaysRequest implements Serializable {
    @NotNull
    private Long employeeId;
    @NotNull
    private int numberOfDays;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
}