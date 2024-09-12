package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.response.student.StudentResponse;
import tech.schoolapi.entity.Student;
import tech.schoolapi.repository.StudentRepository;
import tech.schoolapi.service.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addStudent(Student student) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Student> hasStudent = studentRepository.findById(student.getId());
        try {
            if(hasStudent.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            StudentResponse result = modelMapperService.forResponse().map(studentRepository.save(student), StudentResponse.class);
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
    public ResponseEntity updateStudent(Student student) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        try {
            if(optionalStudent.isPresent()) {
                studentRepository.saveAndFlush(student);
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
    public ResponseEntity deleteStudent(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasStudent = studentRepository.existsById(id);
        try {
            if(hasStudent) {
                studentRepository.deleteById(id);
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
    public ResponseEntity findStudent(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(student, StudentResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllStudents() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> converted = new ArrayList<>();
        if(students.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Student student : students){
            converted.add( modelMapperService.forResponse().map(student, StudentResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);

    }
}
