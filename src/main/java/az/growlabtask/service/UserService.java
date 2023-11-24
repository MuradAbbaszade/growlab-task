package az.growlabtask.service;

import az.growlabtask.dto.SignUpRequest;
import az.growlabtask.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User signUp(SignUpRequest signUpRequest);
    String createRefreshToken(String username);
}
