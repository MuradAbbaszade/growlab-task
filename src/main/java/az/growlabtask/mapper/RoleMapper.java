package az.growlabtask.mapper;

import az.growlabtask.dto.request.RoleRequest;
import az.growlabtask.entity.Role;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest roleRequest);
}
