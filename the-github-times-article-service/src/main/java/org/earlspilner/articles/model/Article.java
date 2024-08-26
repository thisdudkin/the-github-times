package org.earlspilner.articles.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
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
    @Column(name = "author", nullable = false, updatable = false)
    private Integer author;

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

    public Article() { }

    public Article(Integer id, Integer author, String title, String summary, String content,
                   Instant createdUtc, List<Comment> comments, Set<Category> categories, Set<Tag> tags) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.createdUtc = createdUtc;
        this.comments = comments;
        this.categories = categories;
        this.tags = tags;
    }

    @Setter
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "article_categories",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @Setter
    @ManyToMany
    @JoinTable(
            name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Article{");
        sb.append("id=").append(id);
        sb.append(", author=").append(author);
        sb.append(", title='").append(title).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdUtc=").append(createdUtc);
        sb.append(", comments=").append(comments);
        sb.append(", categories=").append(categories);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }

}
