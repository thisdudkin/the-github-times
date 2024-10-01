package dev.earlspilner.users.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static dev.earlspilner.users.model.UserRole.ROLE_USER;

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
    @NotEmpty
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Setter
    @NotEmpty
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Setter
    @NotEmpty
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
    @Column(name = "user_role")
    private List<UserRole> userRoles = Collections.singletonList(ROLE_USER);

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    public User() {
    }

    public User(Integer id, String username, String email,
                String password, Instant createdUtc, Instant updatedUtc, List<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdUtc = createdUtc;
        this.updatedUtc = updatedUtc;
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(createdUtc, user.createdUtc) && Objects.equals(updatedUtc, user.updatedUtc) && Objects.equals(userRoles, user.userRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, createdUtc, updatedUtc, userRoles);
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
