package tech.schoolapi.dto.request.attendance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tech.schoolapi.entity.enums.AttendanceType;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceUpdateRequest {
    @NonNull
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    private AttendanceType type;

    @NonNull
    private AttendanceStudentRequest student;

    @NonNull
    private AttendanceCourseRequest course;

}
