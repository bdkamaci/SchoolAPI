package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Student;

public interface AttendanceService {

    public ResponseEntity addAttendance(Student student);

    public ResponseEntity updateAttendance(Student student);

    public ResponseEntity deleteAttendance(Long id);

    public ResponseEntity findAttendance(Long id);

    public ResponseEntity findAllAttendances();
}
