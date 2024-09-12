package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.enrollment.EnrollmentSaveRequest;
import tech.schoolapi.dto.request.enrollment.EnrollmentUpdateRequest;
import tech.schoolapi.entity.Enrollment;
import tech.schoolapi.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return enrollmentService.findAllEnrollments();
    }

    @GetMapping("/{id}")
    public ResponseEntity findEnrollment(@PathVariable Long id) {
        return enrollmentService.findEnrollment(id);
    }

    @PostMapping()
    public ResponseEntity addEnrollment(@RequestBody EnrollmentSaveRequest enrollment) {
        return enrollmentService.addEnrollment(modelMapperService.forRequest().map(enrollment, Enrollment.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateEnrollment(@RequestBody EnrollmentUpdateRequest enrollment) {
        return enrollmentService.updateEnrollment(modelMapperService.forRequest().map(enrollment, Enrollment.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEnrollment(@PathVariable Long id) {
        return enrollmentService.deleteEnrollment(id);
    }
}
