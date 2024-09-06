package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Student;

public interface EnrollmentService {

    public ResponseEntity addEnrollment(Student student);

    public ResponseEntity updateEnrollment(Student student);

    public ResponseEntity deleteEnrollment(Long id);

    public ResponseEntity findEnrollment(Long id);

    public ResponseEntity findAllEnrollments();
}
