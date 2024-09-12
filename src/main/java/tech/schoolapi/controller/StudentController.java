package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.student.StudentSaveRequest;
import tech.schoolapi.dto.request.student.StudentUpdateRequest;
import tech.schoolapi.entity.Student;
import tech.schoolapi.service.StudentService;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity findStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @PostMapping()
    public ResponseEntity addStudent(@RequestBody StudentSaveRequest student) {
        return studentService.addStudent(modelMapperService.forRequest().map(student, Student.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@RequestBody StudentUpdateRequest student) {
        return studentService.updateStudent(modelMapperService.forRequest().map(student, Student.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}
