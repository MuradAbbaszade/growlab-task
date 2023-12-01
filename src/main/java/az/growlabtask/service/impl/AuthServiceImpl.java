package az.growlabtask.service.impl;

import az.growlabtask.dto.response.JwtResponse;
import az.growlabtask.dto.request.SignInRequest;
import az.growlabtask.dto.request.SignUpRequest;
import az.growlabtask.entity.Role;
import az.growlabtask.entity.User;
import az.growlabtask.enums.AuthStatus;
import az.growlabtask.enums.Status;
import az.growlabtask.repository.RoleRepository;
import az.growlabtask.repository.UserRepository;
import az.growlabtask.service.AuthService;
import az.growlabtask.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final JwtUtil jwtUtil;
    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        userRepository.findByEmail(signUpRequest.getEmail()).ifPresent(account -> {
            throw new IllegalArgumentException("Email used");
        });
        userRepository.findByUsername(signUpRequest.getUsername()).ifPresent(account -> {
            throw new IllegalArgumentException("Username used");
        });
        Role role = roleRepository.findByRole(az.growlabtask.enums.Role.ROLE_USER.toString()).orElse(
                Role.builder()
                        .role(az.growlabtask.enums.Role.ROLE_USER.toString())
                        .createdBy(null)
                        .status(Status.ACTIVE)
                        .build()
        );
        roleRepository.save(role);
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        modelMapper.map(signUpRequest,user);
        user.setRoleSet(Set.of(role));
        user.setAuthStatus(AuthStatus.APPROVED);
        user.setCreatedBy(null);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return user;
    }
    @Override
    public String createRefreshToken(String username) {
        return jwtUtil.generateRefreshTokenFromUsername(username);
    }

    @Override
    public JwtResponse signIn(SignInRequest signInRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()
                )
        );
        String refreshToken = createRefreshToken(signInRequest.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateToken(authentication);
        return new JwtResponse(token,refreshToken);
    }
}
