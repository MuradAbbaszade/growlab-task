package az.growlabtask.service;

import az.growlabtask.dto.JwtResponse;
import az.growlabtask.dto.SignInRequest;
import az.growlabtask.dto.SignUpRequest;
import az.growlabtask.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User signUp(SignUpRequest signUpRequest);
    String createRefreshToken(String username);

    JwtResponse signIn(SignInRequest signInRequest);
}
