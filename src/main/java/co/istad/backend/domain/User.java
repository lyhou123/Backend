package co.istad.backend.domain;

import co.istad.backend.config.jpa.Auditable;
import co.istad.backend.domain.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 36)
    private String uuid;

    @Column(nullable = false,length = 50,unique = true)
    private String userName;

    @Column(nullable = false, length = 100)
    private String email;

    private String profile;

    @Column(nullable = false)
    private String password;


    private Boolean isDeleted;
    private Boolean isVerified;
    private Boolean isActive;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;


    // relationship with role
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id",nullable = false
            )
    )
    private Set<Role> roles = new HashSet<>();


    //relationship with other table product
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Product> products = new HashSet<>();
}
