package tech.schoolapi.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.AttendanceResponse;
import tech.schoolapi.entity.Attendance;
import tech.schoolapi.repository.AttendanceRepository;
import tech.schoolapi.service.AttendanceService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public ResponseEntity addAttendance(Attendance attendance) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Attendance> hasAttendance = attendanceRepository.findById(attendance.getId());
        try {
            if(hasAttendance.isPresent()) {
                hashMap.put("Status", false);
                hashMap.put("Message", "Existing Record!");
                return new ResponseEntity<>(hashMap, HttpStatus.ALREADY_REPORTED);
            }
            AttendanceResponse result = modelMapperService.forResponse().map(attendanceRepository.save(attendance), AttendanceResponse.class);
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
    public ResponseEntity updateAttendance(Attendance attendance) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendance.getId());
        try {
            if(optionalAttendance.isPresent()) {
                attendanceRepository.saveAndFlush(attendance);
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
    public ResponseEntity deleteAttendance(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean hasAttendance = attendanceRepository.existsById(id);
        try {
            if(hasAttendance) {
                attendanceRepository.deleteById(id);
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
    public ResponseEntity findAttendance(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Attendance attendance = attendanceRepository.findById(id).orElse(null);
        if(attendance != null) {
            hashMap.put("Status", true);
            hashMap.put("Result", modelMapperService.forResponse().map(attendance, AttendanceResponse.class));
            return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
        }
        hashMap.put("Status", false);
        hashMap.put("Message", "Record Not Found!");
        return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity findAllAttendances() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Attendance> attendances = attendanceRepository.findAll();
        List<AttendanceResponse> converted = new ArrayList<>();
        if(attendances.isEmpty()){
            hashMap.put("Status", false);
            hashMap.put("Message", "Records Not Found!");
            return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
        }
        hashMap.put("Status", true);
        for(Attendance attendance : attendances){
            converted.add( modelMapperService.forResponse().map(attendance, AttendanceResponse.class));
        }
        hashMap.put("Result", converted);
        return new ResponseEntity<>(hashMap, HttpStatus.FOUND);
    }
}
