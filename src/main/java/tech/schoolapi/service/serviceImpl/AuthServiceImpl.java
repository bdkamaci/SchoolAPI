package tech.schoolapi.service.serviceImpl;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.schoolapi.dto.request.user.LoginRequest;
import tech.schoolapi.entity.User;
import tech.schoolapi.repository.UserRepository;
import tech.schoolapi.security.JwtTokenProvider;
import tech.schoolapi.service.AuthService;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            return jwtTokenProvider.generateToken(loginRequest.getUsername());
        }
        return null;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            jwtTokenProvider.invalidateToken(token);
        }
    }

    @Override
    public void register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
