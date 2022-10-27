package hr.luminumict.pdfgenerator.model.repository;

import hr.luminumict.pdfgenerator.model.dto.VacationDaysDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationDaysRepository extends JpaRepository<VacationDaysDto, Long> {
}