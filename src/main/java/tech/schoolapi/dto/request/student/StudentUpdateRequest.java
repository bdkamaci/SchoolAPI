package tech.schoolapi.dto.request.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentUpdateRequest {
    @NonNull
    private Long id;

    @NonNull
    private Long number;

    @NonNull
    private String s_class;

    @NonNull
    private String address;

    @NonNull
    private LocalDate admission_date;

    @NonNull
    private StudentUserRequest user;
}
