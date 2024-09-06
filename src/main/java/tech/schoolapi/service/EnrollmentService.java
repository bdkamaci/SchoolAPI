package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Enrollment;
import tech.schoolapi.entity.Student;

public interface EnrollmentService {

    public ResponseEntity addEnrollment(Enrollment enrollment);

    public ResponseEntity updateEnrollment(Enrollment enrollment);

    public ResponseEntity deleteEnrollment(Long id);

    public ResponseEntity findEnrollment(Long id);

    public ResponseEntity findAllEnrollments();
}
