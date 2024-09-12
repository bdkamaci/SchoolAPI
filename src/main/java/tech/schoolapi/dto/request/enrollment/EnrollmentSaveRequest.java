package tech.schoolapi.dto.request.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tech.schoolapi.entity.enums.EnrollmentStatus;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnrollmentSaveRequest {
    @NonNull
    private LocalDate date;

    @NonNull
    private EnrollmentStatus status;

    @NonNull
    private EnrollmentStudentRequest student;

    @NonNull
    private EnrollmentCourseRequest course;
}
