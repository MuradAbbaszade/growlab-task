package az.growlabtask.config;

import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Module;
import az.growlabtask.entity.Role;
import az.growlabtask.entity.User;
import az.growlabtask.exception.CustomNotFoundException;
import az.growlabtask.repository.ModuleRepository;
import az.growlabtask.repository.RoleRepository;
import az.growlabtask.repository.UserRepository;
import az.growlabtask.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Component
@AllArgsConstructor
public class CustomAuthorizationManager implements AuthorizationManager {
    private final JwtUtil jwtUtil;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private List<String> moduleName;
    private List<String> roles;
    @Override
    public AuthorizationDecision check(Supplier authentication, Object object) {
        Module module = moduleRepository.findByName(moduleName.get(0))
                .orElseThrow(() -> new CustomNotFoundException("Module not found!"));
        RequestAuthorizationContext requestAuthorizationContext = (RequestAuthorizationContext) object;
        Set<Attribute> moduleAttributeSet = module.getAttributes();
        String jwt = jwtUtil.parseJwt(requestAuthorizationContext.getRequest());
        String email = jwtUtil.getUsernameFromToken(jwt);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomNotFoundException("User not found!"));
        for (String role : roles){
            Role rol=roleRepository.findByRole(role)
                    .orElseThrow(() -> new CustomNotFoundException("Role not found!"));
            if(!user.getRoleSet().contains(rol)){
                return new AuthorizationDecision(false);
            }
        }
        Set<Attribute> userAttributeSet = user.getAttributes();
        boolean attributeIsExist = false;
        for (Attribute moduleAttribute : moduleAttributeSet) {
            for (Attribute userAttribute : userAttributeSet) {
                if (moduleAttribute.equals(userAttribute)) {
                    attributeIsExist = true;
                    break;
                } else attributeIsExist = false;
            }
            if (attributeIsExist == false)
                break;
        }
        return new AuthorizationDecision(attributeIsExist);
    }
}
