package org.raddan.newspaper.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author Alexander Dudkin
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_id_seq")
    @SequenceGenerator(name = "profile_id_seq", sequenceName = "profile_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"password", "enabled", "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar_url")
    private String avatar;

    @Column(name = "bio", columnDefinition = "text")
    private String bio;

    @Column(name = "created_utc", nullable = false)
    private Long createdUtc;

    @Column(name = "updated_utc")
    private Long updatedUtc;
}
