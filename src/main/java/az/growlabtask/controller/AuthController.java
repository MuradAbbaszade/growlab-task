package az.growlabtask.controller;

import az.growlabtask.dto.response.JwtResponse;
import az.growlabtask.dto.request.SignInRequest;
import az.growlabtask.dto.request.SignUpRequest;
import az.growlabtask.dto.request.TokenRefreshRequest;
import az.growlabtask.entity.User;
import az.growlabtask.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public User signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }
    @PostMapping("/sign-in")
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }
    @PostMapping("/refresh-token")
    public JwtResponse refreshToken(@RequestBody TokenRefreshRequest request) {
        return authService.refreshToken(request);
    }
}
