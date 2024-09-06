package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Course;

public interface CourseService {

    public ResponseEntity addCourse(Course course);

    public ResponseEntity updateCourse(Course course);

    public ResponseEntity deleteCourse(Long id);

    public ResponseEntity findCourse(Long id);

    public ResponseEntity findAllCourses();
}
