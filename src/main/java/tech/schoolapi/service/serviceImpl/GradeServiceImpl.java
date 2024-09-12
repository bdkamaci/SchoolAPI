package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.GradeResponse;
import tech.schoolapi.entity.Grade;
import tech.schoolapi.repository.GradeRepository;
import tech.schoolapi.service.GradeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {
    public final GradeRepository gradeRepository;
    public final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addGrade(Grade grade) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Grade> hasGrade = gradeRepository.findById(grade.getId());
        try {
            if(hasGrade.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            GradeResponse result = modelMapperService.forResponse().map(gradeRepository.save(grade), GradeResponse.class);
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
    public ResponseEntity updateGrade(Grade grade) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Grade> optionalGrade = gradeRepository.findById(grade.getId());
        try {
            if(optionalGrade.isPresent()) {
                gradeRepository.saveAndFlush(grade);
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
    public ResponseEntity deleteGrade(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasGrade = gradeRepository.existsById(id);
        try {
            if(hasGrade) {
                gradeRepository.deleteById(id);
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
    public ResponseEntity findGrade(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Grade grade = gradeRepository.findById(id).orElse(null);
        if(grade != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(grade, GradeResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllGrades() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Grade> grades = gradeRepository.findAll();
        List<GradeResponse> converted = new ArrayList<>();
        if(grades.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Grade grade : grades){
            converted.add( modelMapperService.forResponse().map(grade, GradeResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);

    }
}
