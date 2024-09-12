package tech.schoolapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.attendance.AttendanceSaveRequest;
import tech.schoolapi.dto.request.attendance.AttendanceUpdateRequest;
import tech.schoolapi.entity.Attendance;
import tech.schoolapi.service.AttendanceService;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final ModelMapperService modelMapperService;

    @GetMapping()
    public ResponseEntity findAll() {
        return attendanceService.findAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity findAttendance(@PathVariable Long id) {
        return attendanceService.findAttendance(id);
    }

    @PostMapping()
    public ResponseEntity addAttendance(@RequestBody AttendanceSaveRequest attendance) {
        return attendanceService.addAttendance(modelMapperService.forRequest().map(attendance, Attendance.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAttendance(@RequestBody AttendanceUpdateRequest attendance) {
        return attendanceService.updateAttendance(modelMapperService.forRequest().map(attendance, Attendance.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAttendance(@PathVariable Long id) {
        return attendanceService.deleteAttendance(id);
    }

}
