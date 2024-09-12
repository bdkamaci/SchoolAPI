package tech.schoolapi.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.schoolapi.entity.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String mail;
    private Role role;
}
