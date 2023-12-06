package az.growlabtask.controller;

import az.growlabtask.dto.request.RoleRequest;
import az.growlabtask.entity.Role;
import az.growlabtask.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;
    @GetMapping
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping("/{roleId}")
    public Role getById(@PathVariable Long roleId) {
        return roleService.getById(roleId);
    }

    @PostMapping
    public Role create(@RequestBody RoleRequest roleRequest) {
        return roleService.create(roleRequest);
    }

    @PutMapping("/add/user")
    public void addRoleToUser(@RequestParam("roleId") Long roleId,
                                   @RequestParam("userId") Long userId){
        roleService.addRoleToUser(roleId,userId);
    }

    @DeleteMapping("/{roleId}")
    public String delete(@PathVariable Long roleId) {
         roleService.delete(roleId);
         return "success";
    }

}
