package az.growlabtask.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@Builder
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    private Long operationId;
    private LocalDateTime eventTime;
    private String oldValue;
    private String newValue;
    @ManyToOne
    @JoinColumn(name = "accepted_by",referencedColumnName = "id")
    private User acceptedBy;
    private LocalDateTime acceptedTime;


}
