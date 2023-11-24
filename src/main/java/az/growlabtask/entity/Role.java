package az.growlabtask.entity;

import az.growlabtask.enums.Status;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createTime;
    @ManyToOne
    @JoinColumn(name = "created_by",referencedColumnName = "id")
    private User createdBy;

    @PrePersist
    public void createTime(){
        this.createTime = LocalDateTime.now();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
