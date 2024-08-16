package org.earlspilner.newspaper.model;

import jakarta.persistence.*;
import lombok.*;
import org.earlspilner.auth.model.AppUser;

import java.time.Instant;

/**
 * @author Alexander Dudkin
 */
@Entity
@Builder
@Getter @Setter
@Table(name = "Profiles")
@NoArgsConstructor
@AllArgsConstructor
public class Profile extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

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
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Profile{");
        sb.append("user=").append(appUser);
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", bio='").append(bio).append('\'');
        sb.append(", createdUtc=").append(createdUtc);
        sb.append(", updatedUtc=").append(updatedUtc);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

}
