package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Grade;

public interface GradeService {

    public ResponseEntity addGrade(Grade grade);

    public ResponseEntity updateGrade(Grade grade);

    public ResponseEntity deleteGrade(Long id);

    public ResponseEntity findGrade(Long id);

    public ResponseEntity findAllGrades();
}
