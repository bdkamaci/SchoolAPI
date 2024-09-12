package tech.schoolapi.dto.response.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.schoolapi.entity.enums.AttendanceType;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceResponse {
    private Long id;
    private LocalDate date;
    private AttendanceType type;
    private AttendanceCourseResponse course;
    private AttendanceStudentResponse student;
}
