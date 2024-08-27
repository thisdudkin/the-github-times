package org.earlspilner.users.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Builder
@Entity @Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "user_role")
    private List<UserRole> userRoles;

    @Setter
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    public User() { }

    public User(Integer id, String username, String email, String password, List<UserRole> userRoles, Profile profile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
        this.profile = profile;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userRoles=").append(userRoles);
        sb.append(", profile=").append(profile);
        sb.append('}');
        return sb.toString();
    }

}
