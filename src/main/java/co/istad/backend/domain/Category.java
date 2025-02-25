package co.istad.backend.domain;

import co.istad.backend.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor

public class Category extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 36)
    private String uuid;

    @Column(nullable = false)
    private String name;

    //relationships with Product
    @OneToMany(mappedBy = "category")
    private List<Product> products;


}
