package hr.luminumict.pdfgenerator.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class VacationDaysDto {
    @Id
    @SequenceGenerator(
            name = "sequence_id_vacation",
            sequenceName = "seq_vacation",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_vacation"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    @Column(
            name = "year",
            nullable = false
    )
    private Long year;
    @Column(
            name = "number_of_days",
            nullable = false
    )
    private Long numberOfDays;
    @Column(
            name = "remaining_days",
            nullable = false
    )
    private Long remainingDays;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}