package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Student;

public interface StudentService {

    public ResponseEntity addStudent(Student student);

    public ResponseEntity updateStudent(Student student);

    public ResponseEntity deleteStudent(Long id);

    public ResponseEntity findStudent(Long id);

    public ResponseEntity findAllStudents();
}
