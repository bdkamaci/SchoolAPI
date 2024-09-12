package tech.schoolapi.dto.response.educator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EducatorUserResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
}
