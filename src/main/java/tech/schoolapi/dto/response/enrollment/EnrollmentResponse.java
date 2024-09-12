package tech.schoolapi.dto.response.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.schoolapi.entity.enums.EnrollmentStatus;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnrollmentResponse {
    private Long id;
    private LocalDate date;
    private EnrollmentStatus status;
    private EnrollmentStudentResponse student;
    private EnrollmentCourseResponse course;
}
