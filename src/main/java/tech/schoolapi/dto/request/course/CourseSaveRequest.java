package tech.schoolapi.dto.request.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseSaveRequest {
    @NonNull
    private String name;

    @NonNull
    private String code;

    @NonNull
    private LocalDateTime schedule;

    @NonNull
    private CourseEducatorRequest educator;
}
