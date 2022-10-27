package hr.luminumict.pdfgenerator.model.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Employee {
    @Id
    @SequenceGenerator(
            name = "sequance_id_employee",
            sequenceName = "seq_employee",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequance_id_employee"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
}
