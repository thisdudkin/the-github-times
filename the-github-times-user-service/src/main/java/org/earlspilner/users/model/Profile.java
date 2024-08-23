package org.earlspilner.users.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Alexander Dudkin
 */
@Builder
@Entity @Getter
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @Setter
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Setter
    @Column(name = "full_name", nullable = false)
    private String name;

    @Setter
    @Column(name = "bio", length = 8192)
    private String bio;

    @Setter
    @Column(name = "location", nullable = false)
    private String location;

    @Setter
    @Column(name = "birth_date", nullable = false, columnDefinition = "date")
    private LocalDate birthDate;

    @Setter
    @Column(name = "website_url")
    private String website;

    @Setter
    @Column(name = "created_utc", nullable = false, updatable = false)
    private Instant createdUtc;

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    public Profile() {
    }

    public Profile(Integer id, User user, String name, String bio,
                   String location, LocalDate birthDate, String website, Instant createdUtc) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.birthDate = birthDate;
        this.website = website;
        this.createdUtc = createdUtc;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user.getUsername() +
                ", bio='" + bio + '\'' +
                ", birthDate=" + birthDate +
                ", location='" + location + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

}