package hr.luminumict.pdfgenerator.model.repository;

import hr.luminumict.pdfgenerator.model.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
