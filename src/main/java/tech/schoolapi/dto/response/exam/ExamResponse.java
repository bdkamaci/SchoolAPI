package tech.schoolapi.dto.response.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.schoolapi.entity.enums.ExamType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamResponse {
    private Long id;
    private LocalDateTime date;
    private ExamType type;
    private ExamCourseResponse course;
}
