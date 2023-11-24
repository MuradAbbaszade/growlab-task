package az.growlab.growlabtask.entity;

import az.growlab.growlabtask.enums.AuthStatus;
import az.growlab.growlabtask.enums.Status;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Status status;
    private Long createdBy;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")
    )
    private Set<Role> roleSet;
    @CreationTimestamp
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
    private AuthStatus authStatus;


}
