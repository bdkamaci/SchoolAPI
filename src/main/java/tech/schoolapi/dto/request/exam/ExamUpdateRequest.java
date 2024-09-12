package tech.schoolapi.dto.request.exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tech.schoolapi.entity.enums.ExamType;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamUpdateRequest {
    @NonNull
    private Long id;

    @NonNull
    private LocalDateTime date;

    @NonNull
    private ExamType type;

    @NonNull
    private ExamCourseRequest course;
}
