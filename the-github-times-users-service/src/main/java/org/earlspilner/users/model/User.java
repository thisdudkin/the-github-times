package org.earlspilner.users.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String username;

    @Getter @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Getter @Setter
    @Column(nullable = false)
    private String password;

    @Getter @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;

    public User() { }

    public User(Integer id, String username, String email, String password, List<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }

}
