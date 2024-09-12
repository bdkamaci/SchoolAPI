package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.EnrollmentResponse;
import tech.schoolapi.entity.Enrollment;
import tech.schoolapi.repository.EnrollmentRepository;
import tech.schoolapi.service.EnrollmentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addEnrollment(Enrollment enrollment) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Enrollment> hasEnrollment = enrollmentRepository.findById(enrollment.getId());
        try {
            if(hasEnrollment.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            EnrollmentResponse result = modelMapperService.forResponse().map(enrollmentRepository.save(enrollment), EnrollmentResponse.class);
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
    public ResponseEntity updateEnrollment(Enrollment enrollment) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollment.getId());
        try {
            if(optionalEnrollment.isPresent()) {
                enrollmentRepository.saveAndFlush(enrollment);
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
    public ResponseEntity deleteEnrollment(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasEnrollment = enrollmentRepository.existsById(id);
        try {
            if(hasEnrollment) {
                enrollmentRepository.deleteById(id);
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
    public ResponseEntity findEnrollment(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        if(enrollment != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(enrollment, EnrollmentResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllEnrollments() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<EnrollmentResponse> converted = new ArrayList<>();
        if(enrollments.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Enrollment enrollment : enrollments){
            converted.add( modelMapperService.forResponse().map(enrollment, EnrollmentResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
