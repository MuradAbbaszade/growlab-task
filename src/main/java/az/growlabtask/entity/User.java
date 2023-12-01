package az.growlabtask.entity;

import az.growlabtask.enums.AuthStatus;
import az.growlabtask.enums.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Long createdBy;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private Set<Role> roleSet;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;
    @Enumerated(EnumType.STRING)
    private AuthStatus authStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_attribute",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id",referencedColumnName = "id")
    )
    private Set<Attribute> attributes;

    @PrePersist
    public void createTime(){
        this.createTime = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void changeLastUpdateTime(){
        this.lastUpdateTime = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleSet;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.authStatus == AuthStatus.APPROVED) return true;
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (this.status == Status.ACTIVE) return true;
        return false;
    }
}
