package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.course.CourseSaveRequest;
import tech.schoolapi.dto.request.course.CourseUpdateRequest;
import tech.schoolapi.entity.Course;
import tech.schoolapi.service.CourseService;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity findCourse(@PathVariable Long id) {
        return courseService.findCourse(id);
    }

    @PostMapping()
    public ResponseEntity addCourse(@RequestBody CourseSaveRequest course) {
        return courseService.addCourse(modelMapperService.forRequest().map(course, Course.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody CourseUpdateRequest course) {
        return courseService.updateCourse(modelMapperService.forRequest().map(course, Course.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }

}
