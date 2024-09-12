package tech.schoolapi.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import tech.schoolapi.entity.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {
    @NonNull
    private String username;

    @NonNull
    private String name;

    @NonNull
    private String surname;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private Role role;
}
