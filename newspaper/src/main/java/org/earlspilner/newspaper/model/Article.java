package org.earlspilner.newspaper.model;

import jakarta.persistence.*;
import lombok.*;
import org.earlspilner.auth.model.AppUser;

import java.time.Instant;
import java.util.Set;

/**
 * @author Alexander Dudkin
 */
@Entity
@Builder
@Getter @Setter
@Table(name = "Articles")
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity {

    @Column(name = "title", columnDefinition = "text", nullable = false)
    private String title;

    @Column(name = "summary", columnDefinition = "text", nullable = false)
    private String summary;

    @Column(name = "content", columnDefinition = "text", nullable = false)
    private String content;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AppUser appUser;

    @Column(name = "created_utc", nullable = false)
    private Instant createdUtc;

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

    @ManyToMany
    @JoinTable(
            name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

}
