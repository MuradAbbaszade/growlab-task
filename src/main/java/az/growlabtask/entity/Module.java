package az.growlabtask.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "module_attribute",
            joinColumns = @JoinColumn(name = "module_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id",referencedColumnName = "id")
    )
    private Set<Attribute> attributes;
}
