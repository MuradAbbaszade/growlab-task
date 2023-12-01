package az.growlabtask.repository;

import az.growlabtask.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM module_role WHERE module_id = :moduleId AND attribute_id = :attributeId AND role_id = :roleId", nativeQuery = true)
    boolean existsByModuleIdAndAttributeIdAndRoleId(
            @Param("moduleId") Long moduleId, @Param("attributeId") Long attributeId, @Param("roleId") Long roleId);

    Optional<Module> findByName(String name);

}
