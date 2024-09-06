package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
