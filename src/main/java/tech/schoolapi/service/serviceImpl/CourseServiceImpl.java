package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.response.course.CourseResponse;
import tech.schoolapi.entity.Course;
import tech.schoolapi.repository.CourseRepository;
import tech.schoolapi.service.CourseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addCourse(Course course) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Course> hasCourse = courseRepository.findById(course.getId());
        try {
            if(hasCourse.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            CourseResponse result = modelMapperService.forResponse().map(courseRepository.save(course), CourseResponse.class);
            hashMap.put("Status", true);
            hashMap.put("Message", "Record Created!");
            hashMap.put("Result", result);
            return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be saved!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateCourse(Course course) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Course> optionalCourse = courseRepository.findById(course.getId());
        try {
            if(optionalCourse.isPresent()) {
                courseRepository.saveAndFlush(course);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been updated!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be updated!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deleteCourse(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasCourse = courseRepository.existsById(id);
        try {
            if(hasCourse) {
                courseRepository.deleteById(id);
                hashMap.put("Status", true);
                hashMap.put("Message", "Record has been deleted!");
                return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }
            hashMap.put("Status", false);
            hashMap.put("Message", "Record Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            hashMap.put("Status", false);
            hashMap.put("Error", "Record could not be deleted!");
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity findCourse(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Course course = courseRepository.findById(id).orElse(null);
        if(course != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(course, CourseResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllCourses() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> converted = new ArrayList<>();
        if(courses.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Course course : courses){
            converted.add( modelMapperService.forResponse().map(course, CourseResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
