package co.istad.backend.domain;

import co.istad.backend.config.jpa.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Setter
@Getter

public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private String thumbnail;


    //relationships with other table user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    //relationships with other table category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


}
