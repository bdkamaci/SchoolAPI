package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
