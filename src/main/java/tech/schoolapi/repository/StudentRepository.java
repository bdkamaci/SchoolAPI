package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
