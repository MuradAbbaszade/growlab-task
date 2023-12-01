package az.growlabtask.controller;

import az.growlabtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final UserRepository userRepository;

    @GetMapping("/a")
    public String a(){
        /*Set<Role> roleSet = user.getRoleSet();
        for (Role role:roleSet){
            System.out.println(role.getAttributeSet());
        }*/
        return "customer";
    }

    @GetMapping
    public String customer(){
        System.out.println("it works");
        return "as";
    }
}
