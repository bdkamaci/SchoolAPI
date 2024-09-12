package tech.schoolapi.dto.request.grade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeUpdateRequest {
    @NonNull
    private Long id;

    @NonNull
    private Integer score;

    @NonNull
    private GradeStudentRequest student;

    @NonNull
    private GradeCourseRequest course;

    @NonNull
    private GradeExamRequest exam;
}
