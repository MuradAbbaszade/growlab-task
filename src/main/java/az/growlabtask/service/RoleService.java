package az.growlabtask.service;

import az.growlabtask.dto.request.RoleRequest;
import az.growlabtask.entity.Role;

import java.util.List;

public interface RoleService {
    Role create(RoleRequest roleRequest);
    List<Role> getAll();
    Role  getById(Long roleId);

    void update(Long roleId,RoleRequest roleRequest);
    void delete(Long roleId);
}
