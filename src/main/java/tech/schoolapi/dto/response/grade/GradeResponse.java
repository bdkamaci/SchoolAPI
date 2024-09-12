package tech.schoolapi.dto.response.grade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GradeResponse {
    private Long id;
    private Integer score;
    private GradeStudentResponse student;
    private GradeCourseResponse course;
    private GradeExamResponse exam;
}
