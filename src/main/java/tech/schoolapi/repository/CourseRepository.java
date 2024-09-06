package tech.schoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.schoolapi.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
