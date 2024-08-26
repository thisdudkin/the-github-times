package org.earlspilner.articles.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author Alexander Dudkin
 */
@Entity @Getter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false, updatable = false)
    private Article article;

    @Setter
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer userId;

    @Setter
    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Setter
    @Column(name = "created_utc", nullable = false, updatable = false)
    private Instant createdUtc;

    public Comment() { }

    public Comment(Integer id, Article article, Integer userId, String content, Instant createdUtc) {
        this.id = id;
        this.article = article;
        this.userId = userId;
        this.content = content;
        this.createdUtc = createdUtc;
    }

    @PrePersist
    protected void onCreate() {
        this.createdUtc = Instant.now();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Comment{");
        sb.append("id=").append(id);
        sb.append(", article=").append(article.getId());
        sb.append(", userId=").append(userId);
        sb.append(", content='").append(content).append('\'');
        sb.append(", createdUtc=").append(createdUtc);
        sb.append('}');
        return sb.toString();
    }

}
