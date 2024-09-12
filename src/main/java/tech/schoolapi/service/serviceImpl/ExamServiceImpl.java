package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.ExamResponse;
import tech.schoolapi.entity.Exam;
import tech.schoolapi.repository.ExamRepository;
import tech.schoolapi.service.ExamService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addExam(Exam exam) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Exam> hasExam = examRepository.findById(exam.getId());
        try {
            if(hasExam.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            ExamResponse result = modelMapperService.forResponse().map(examRepository.save(exam), ExamResponse.class);
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
    public ResponseEntity updateExam(Exam exam) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Exam> optionalExam = examRepository.findById(exam.getId());
        try {
            if(optionalExam.isPresent()) {
                examRepository.saveAndFlush(exam);
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
    public ResponseEntity deleteExam(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasExam = examRepository.existsById(id);
        try {
            if(hasExam) {
                examRepository.deleteById(id);
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
    public ResponseEntity findExam(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Exam exam = examRepository.findById(id).orElse(null);
        if(exam != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(exam, ExamResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllExams() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Exam> exams = examRepository.findAll();
        List<ExamResponse> converted = new ArrayList<>();
        if(exams.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Exam exam : exams){
            converted.add( modelMapperService.forResponse().map(exam, ExamResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
