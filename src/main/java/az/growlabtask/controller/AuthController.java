package az.growlabtask.controller;

import az.growlabtask.dto.response.JwtResponse;
import az.growlabtask.dto.request.SignInRequest;
import az.growlabtask.dto.request.SignUpRequest;
import az.growlabtask.dto.request.TokenRefreshRequest;
import az.growlabtask.entity.User;
import az.growlabtask.service.AuthService;
import az.growlabtask.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
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
        String requestRefreshToken = request.getRefreshToken();
        jwtUtil.validateToken(requestRefreshToken);

        String username=jwtUtil.extractClaims(request.getRefreshToken()).getSubject();
        String token = jwtUtil.generateTokenFromUsername(username);
        requestRefreshToken = jwtUtil.generateRefreshTokenFromUsername(username);
        return new JwtResponse(token, requestRefreshToken);

    }
}
