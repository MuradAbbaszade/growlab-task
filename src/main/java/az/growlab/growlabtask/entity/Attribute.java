package az.growlab.growlabtask.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attribute;
    @ManyToOne
    @JoinColumn(name = "module_id",referencedColumnName = "id")
    private Module module;
}
