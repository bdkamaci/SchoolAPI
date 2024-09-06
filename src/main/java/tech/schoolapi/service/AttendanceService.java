package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Attendance;
import tech.schoolapi.entity.Student;

public interface AttendanceService {

    public ResponseEntity addAttendance(Attendance attendance);

    public ResponseEntity updateAttendance(Attendance attendance);

    public ResponseEntity deleteAttendance(Long id);

    public ResponseEntity findAttendance(Long id);

    public ResponseEntity findAllAttendances();
}
