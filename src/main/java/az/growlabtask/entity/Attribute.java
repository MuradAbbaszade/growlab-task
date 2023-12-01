package az.growlabtask.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attribute;
}
