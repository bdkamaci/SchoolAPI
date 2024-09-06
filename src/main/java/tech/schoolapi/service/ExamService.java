package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Student;

public interface ExamService {

    public ResponseEntity addExam(Student student);

    public ResponseEntity updateExam(Student student);

    public ResponseEntity deleteExam(Long id);

    public ResponseEntity findExam(Long id);

    public ResponseEntity findAllExams();
}
