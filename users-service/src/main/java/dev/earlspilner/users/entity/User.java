package dev.earlspilner.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

import static dev.earlspilner.users.entity.UserRole.ROLE_USER;

/**
 * @author Alexander Dudkin
 */
@Entity @Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Setter
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Setter
    @Column(name = "created_utc", nullable = false, updatable = false)
    private Instant createdUtc;

    @Setter
    @Column(name = "updated_utc")
    private Instant updatedUtc;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"))
    private List<UserRole> userRoles;

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
        this.userRoles = List.of(ROLE_USER);
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdUtc=" + createdUtc +
                ", updatedUtc=" + updatedUtc +
                ", userRoles=" + userRoles +
                '}';
    }
}
