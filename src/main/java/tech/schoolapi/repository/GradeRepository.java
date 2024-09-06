package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
