package tech.schoolapi.service;

import org.springframework.http.ResponseEntity;
import tech.schoolapi.entity.Exam;

public interface ExamService {

    public ResponseEntity addExam(Exam exam);

    public ResponseEntity updateExam(Exam exam);

    public ResponseEntity deleteExam(Long id);

    public ResponseEntity findExam(Long id);

    public ResponseEntity findAllExams();
}
