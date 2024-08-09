package org.raddan.newspaper.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Alexander Dudkin
 */
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq")
    @SequenceGenerator(name = "profile_id_seq", sequenceName = "profile_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "enabled", "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar_url")
    private String avatar;

    @Column(name = "bio", columnDefinition = "text")
    private String bio;

    @Column(name = "created_utc", nullable = false, updatable = false)
    @Builder.Default
    private Instant createdUtc = Instant.now();

    @Column(name = "updated_utc")
    @Builder.Default
    private Instant updatedUtc = Instant.now();

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
        this.updatedUtc = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) &&
                Objects.equals(user, profile.user) &&
                Objects.equals(fullName, profile.fullName) &&
                Objects.equals(avatar, profile.avatar) &&
                Objects.equals(bio, profile.bio) &&
                Objects.equals(createdUtc, profile.createdUtc) &&
                Objects.equals(updatedUtc, profile.updatedUtc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, fullName, avatar, bio, createdUtc, updatedUtc);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", user=" + (user != null ? user.getId() : null) +
                ", fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", bio='" + bio + '\'' +
                ", createdUtc=" + createdUtc +
                ", updatedUtc=" + updatedUtc +
                '}';
    }

}
