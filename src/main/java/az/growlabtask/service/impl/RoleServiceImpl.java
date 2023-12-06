package az.growlabtask.service.impl;

import az.growlabtask.dto.request.RoleRequest;
import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Role;
import az.growlabtask.entity.User;
import az.growlabtask.exception.CustomNotFoundException;
import az.growlabtask.mapper.RoleMapper;
import az.growlabtask.repository.RoleRepository;
import az.growlabtask.repository.UserRepository;
import az.growlabtask.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role create(RoleRequest roleRequest) {
        if (!roleRepository.findByRole(roleRequest.getRole()).isPresent()) {
            Role role = roleMapper.toRole(roleRequest);
            return roleRepository.save(role);
        }
        throw new IllegalArgumentException("Role already exist");
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomNotFoundException("Role not found!"));
    }

    @Override
    public void addRoleToUser(Long roleId, Long userId) {
        Role role = getById(roleId);
        User user = userRepository.findById(userId).get();
        user.getRoleSet().add(role);
        userRepository.save(user);
    }

    @Override
    public void delete(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
