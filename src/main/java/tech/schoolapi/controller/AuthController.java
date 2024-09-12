package tech.schoolapi.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.schoolapi.core.config.modelMapper.ModelMapperService;
import tech.schoolapi.dto.request.user.LoginRequest;
import tech.schoolapi.dto.response.user.LoginResponse;
import tech.schoolapi.dto.request.user.RegisterRequest;
import tech.schoolapi.entity.User;
import tech.schoolapi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Resource
    private AuthService authService;
    private final ModelMapperService modelMapperService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        if (token != null) {
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        authService.logout(request);
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.register(modelMapperService.forRequest().map(registerRequest, User.class));
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
