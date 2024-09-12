package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.grade.GradeSaveRequest;
import tech.schoolapi.dto.request.grade.GradeUpdateRequest;
import tech.schoolapi.entity.Grade;
import tech.schoolapi.service.GradeService;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return gradeService.findAllGrades();
    }

    @GetMapping("/{id}")
    public ResponseEntity findGrade(@PathVariable Long id) {
        return gradeService.findGrade(id);
    }

    @PostMapping()
    public ResponseEntity addGrade(@RequestBody GradeSaveRequest grade) {
        return gradeService.addGrade(modelMapperService.forRequest().map(grade, Grade.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGrade(@RequestBody GradeUpdateRequest grade) {
        return gradeService.updateGrade(modelMapperService.forRequest().map(grade, Grade.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGrade(@PathVariable Long id) {
        return gradeService.deleteGrade(id);
    }
}
