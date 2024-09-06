package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Student;

public interface GradeService {

    public ResponseEntity addGrade(Student student);

    public ResponseEntity updateGrade(Student student);

    public ResponseEntity deleteGrade(Long id);

    public ResponseEntity findGrade(Long id);

    public ResponseEntity findAllGrades();
}
