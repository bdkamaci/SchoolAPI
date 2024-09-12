package tech.schoolapi.service;

import jakarta.servlet.http.HttpServletRequest;
import tech.schoolapi.dto.request.user.LoginRequest;
import tech.schoolapi.entity.User;

public interface AuthService {

    public String login(LoginRequest loginRequest);

    public void logout(HttpServletRequest request);

    public void register(User user);
}
