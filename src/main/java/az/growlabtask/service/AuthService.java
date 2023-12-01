package az.growlabtask.service;

import az.growlabtask.dto.request.TokenRefreshRequest;
import az.growlabtask.dto.response.JwtResponse;
import az.growlabtask.dto.request.SignInRequest;
import az.growlabtask.dto.request.SignUpRequest;
import az.growlabtask.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User signUp(SignUpRequest signUpRequest);

    JwtResponse signIn(SignInRequest signInRequest);

    JwtResponse refreshToken(TokenRefreshRequest request);
}
