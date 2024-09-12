package tech.schoolapi.dto.response.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponse {
    private Long id;
    private Long number;
    private String s_class;
    private String address;
    private LocalDate admission_date;
    private StudentUserResponse user;
}
