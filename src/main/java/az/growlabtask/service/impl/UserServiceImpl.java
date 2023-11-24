package az.growlabtask.service.impl;

import az.growlabtask.dto.SignUpRequest;
import az.growlabtask.entity.Role;
import az.growlabtask.entity.User;
import az.growlabtask.enums.AuthStatus;
import az.growlabtask.enums.Status;
import az.growlabtask.repository.RoleRepository;
import az.growlabtask.repository.UserRepository;
import az.growlabtask.service.UserService;
import az.growlabtask.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
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
        Role role = roleRepository.findByRole(az.growlabtask.enums.Role.USER.toString()).orElse(
                Role.builder()
                        .role(az.growlabtask.enums.Role.USER.toString())
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
}
