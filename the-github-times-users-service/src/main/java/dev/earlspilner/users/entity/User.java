package dev.earlspilner.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static dev.earlspilner.users.entity.UserRole.ROLE_USER;

/**
 * @author Alexander Dudkin
 */
@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private List<UserRole> userRoles = Collections.singletonList(ROLE_USER);

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("email", email)
                .append("password", password)
                .append("createdUtc", createdUtc)
                .append("updatedUtc", updatedUtc)
                .append("userRoles", userRoles)
                .toString();
    }

}
