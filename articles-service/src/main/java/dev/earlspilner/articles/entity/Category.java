package dev.earlspilner.articles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.Instant;
import java.util.Set;

/**
 * @author Alexander Dudkin
 */
@Entity @Getter
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(name = "category", nullable = false)
    private String name;

    @Setter
    @Column(name = "created_utc", nullable = false, updatable = false)
    private Instant createdUtc;

    @Setter
    @Column(name = "updated_utc")
    private Instant updatedUtc;

    @ManyToMany(mappedBy = "categories")
    private Set<Article> articles;

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    public Category() {
    }

    public Category(Integer id, String name, Instant createdUtc, Instant updatedUtc, Set<Article> articles) {
        this.id = id;
        this.name = name;
        this.createdUtc = createdUtc;
        this.updatedUtc = updatedUtc;
        this.articles = articles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("createdUtc", createdUtc)
                .append("updatedUtc", updatedUtc)
                .append("articles", articles)
                .toString();
    }

}
