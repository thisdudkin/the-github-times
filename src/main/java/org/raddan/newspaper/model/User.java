package org.raddan.newspaper.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.raddan.newspaper.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Alexander Dudkin
 */
@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User() { }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Profile profile;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIncludeProperties(value = {"id", "title"})
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIncludeProperties(value = {"id"})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIncludeProperties(value = {"id"})
    private List<Rating> ratings = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

}