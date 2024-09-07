package dev.earlspilner.profiles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.Instant;
import java.time.LocalDate;

/**
 * @author Alexander Dudkin
 */
@Entity @Getter
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer userId;

    @Setter
    @Column(name = "full_name", nullable = false)
    private String name;

    @Setter
    @Column(name = "bio", nullable = false, length = 8192)
    private String bio;

    @Setter
    @Column(name = "location", nullable = false)
    private String location;

    @Setter
    @Column(name = "birth_date", nullable = false, columnDefinition = "date")
    private LocalDate birthDate;

    @Setter
    @Column(name = "website_url", nullable = false)
    private String website;

    @Setter
    @Column(name = "created_utc", nullable = false, updatable = false)
    private Instant createdUtc;

    @Setter
    @Column(name = "updated_utc")
    private Instant updatedUtc;

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    public Profile() { }

    public Profile(Integer id, Integer userId, String name, String bio, String location,
                   LocalDate birthDate, String website, Instant createdUtc, Instant updatedUtc) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.bio = bio;
        this.location = location;
        this.birthDate = birthDate;
        this.website = website;
        this.createdUtc = createdUtc;
        this.updatedUtc = updatedUtc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("name", name)
                .append("bio", bio)
                .append("location", location)
                .append("birthDate", birthDate)
                .append("website", website)
                .append("createdUtc", createdUtc)
                .append("updatedUtc", updatedUtc)
                .toString();
    }

}
