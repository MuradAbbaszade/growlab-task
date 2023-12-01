package az.growlabtask.config;

import az.growlabtask.entity.Attribute;
import az.growlabtask.entity.Module;
import az.growlabtask.entity.Role;
import az.growlabtask.entity.User;
import az.growlabtask.enums.Status;
import az.growlabtask.repository.ModuleRepository;
import az.growlabtask.repository.RoleRepository;
import az.growlabtask.repository.UserRepository;
import az.growlabtask.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class CustomerAuthorizationManager implements AuthorizationManager {
    private final JwtUtil jwtUtil;
    private final ModuleRepository moduleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public AuthorizationDecision check(Supplier authentication, Object object) {
        Module module = moduleRepository.findByName("customer").get();
        RequestAuthorizationContext requestAuthorizationContext = (RequestAuthorizationContext) object;
        Set<Attribute> moduleAttributeSet = module.getAttributes();
        String jwt = jwtUtil.parseJwt(requestAuthorizationContext.getRequest());
        String email = jwtUtil.getUsernameFromToken(jwt);
        User user = userRepository.findByEmail(email).get();
        if(!user.getRoleSet().contains(roleRepository.findByRole("ROLE_USER"))){
            return new AuthorizationDecision(false);
        }

        Set<Attribute> userAttributeSet = user.getAttributes();
        boolean attributeIsExist = false;
        for (Attribute moduleAttribute : moduleAttributeSet) {
            for (Attribute userAttribute : userAttributeSet) {
                if (moduleAttribute.equals(userAttribute)) {
                    attributeIsExist = true;
                    continue;
                } else attributeIsExist = false;
            }
            if (attributeIsExist == false)
                break;
        }
        return new AuthorizationDecision(attributeIsExist);
    }
}
