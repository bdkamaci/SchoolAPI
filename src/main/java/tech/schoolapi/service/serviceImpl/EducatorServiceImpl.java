package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.response.EducatorResponse;
import tech.schoolapi.entity.Educator;
import tech.schoolapi.repository.EducatorRepository;
import tech.schoolapi.service.EducatorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EducatorServiceImpl implements EducatorService {

    private final ModelMapperService modelMapperService;
    private final EducatorRepository educatorRepository;

    @Override
    public ResponseEntity addEducator(Educator educator) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Educator> hasEducator = educatorRepository.findById(educator.getId());
        try {
            if(hasEducator.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            EducatorResponse result = modelMapperService.forResponse().map(educatorRepository.save(educator), EducatorResponse.class);
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
    public ResponseEntity updateEducator(Educator educator) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Educator> optionalEducator = educatorRepository.findById(educator.getId());
        try {
            if(optionalEducator.isPresent()) {
                educatorRepository.saveAndFlush(educator);
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
    public ResponseEntity deleteEducator(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasEducator = educatorRepository.existsById(id);
        try {
            if(hasEducator) {
                educatorRepository.deleteById(id);
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
    public ResponseEntity findEducator(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Educator educator = educatorRepository.findById(id).orElse(null);
        if(educator != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(educator, EducatorResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllEducators() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Educator> educators = educatorRepository.findAll();
        List<EducatorResponse> converted = new ArrayList<>();
        if(educators.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Educator educator : educators){
            converted.add( modelMapperService.forResponse().map(educator, EducatorResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);

    }
}
