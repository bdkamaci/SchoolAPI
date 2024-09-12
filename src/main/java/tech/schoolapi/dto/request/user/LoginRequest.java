package tech.schoolapi.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    @NonNull
    private String username;

    @NonNull
    private String password;
}
