package com.MML.Auth;

import com.MML.JWT.JwtService;
import com.MML.User.Role;
import com.MML.User.User;
import com.MML.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public static AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.email)
                .password(request.password)
                .registerDate(request.registerDate)
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
