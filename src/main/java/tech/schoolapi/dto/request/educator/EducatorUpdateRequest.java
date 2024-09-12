package tech.schoolapi.dto.request.educator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tech.schoolapi.entity.enums.Branch;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EducatorUpdateRequest {
    @NonNull
    private Long id;

    @NonNull
    private Branch branch;

    @NonNull
    private EducatorUserRequest user;
}
