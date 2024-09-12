package tech.schoolapi.dto.response.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentUserResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
