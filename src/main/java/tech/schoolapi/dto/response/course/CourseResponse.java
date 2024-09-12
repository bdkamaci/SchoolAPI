package tech.schoolapi.dto.response.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDateTime schedule;
    private CourseEducatorResponse educator;
}
