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
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Column(name = "author_id", nullable = false, updatable = false)
    private Integer authorId;

    @Setter
    @Column(name = "title", nullable = false, length = 8192)
    private String title;

    @Setter
    @Column(name = "summary", nullable = false, length = 8192)
    private String summary;

    @Setter
    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Setter
    @Column(name = "created_utc", nullable = false, updatable = false)
    private Instant createdUtc;

    @Setter
    @Column(name = "updated_utc")
    private Instant updatedUtc;

    @ManyToMany
    @JoinTable(
            name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(
            name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedUtc = Instant.now();
    }

    public Article() {
    }

    public Article(Integer id, Integer authorId, String title, String summary, String content,
                   Instant createdUtc, Instant updatedUtc, Set<Category> categories, Set<Tag> tags) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.createdUtc = createdUtc;
        this.updatedUtc = updatedUtc;
        this.categories = categories;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("authorId", authorId)
                .append("title", title)
                .append("summary", summary)
                .append("content", content)
                .append("createdUtc", createdUtc)
                .append("updatedUtc", updatedUtc)
                .append("categories", categories)
                .append("tags", tags)
                .toString();
    }

}
