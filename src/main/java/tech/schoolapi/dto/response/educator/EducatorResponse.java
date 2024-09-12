package tech.schoolapi.dto.response.educator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.schoolapi.entity.enums.Branch;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EducatorResponse {
    private Long id;
    private Branch branch;
    private EducatorUserResponse user;
}
