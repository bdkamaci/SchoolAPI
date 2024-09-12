package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.exam.ExamSaveRequest;
import tech.schoolapi.dto.request.exam.ExamUpdateRequest;
import tech.schoolapi.entity.Exam;
import tech.schoolapi.service.ExamService;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return examService.findAllExams();
    }

    @GetMapping("/{id}")
    public ResponseEntity findExam(@PathVariable Long id) {
        return examService.findExam(id);
    }

    @PostMapping()
    public ResponseEntity addExam(@RequestBody ExamSaveRequest exam) {
        return examService.addExam(modelMapperService.forRequest().map(exam, Exam.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateExam(@RequestBody ExamUpdateRequest exam) {
        return examService.updateExam(modelMapperService.forRequest().map(exam, Exam.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExam(@PathVariable Long id) {
        return examService.deleteExam(id);
    }
}
