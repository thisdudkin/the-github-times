package org.raddan.newspaper.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.data.NewsDataConverter;

import java.io.Serializable;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News {

    @Id
    @Column(name = "news_id", nullable = false, unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "created_utc", nullable = false)
    private Long createdUtc;

    @Column(name = "updated_utc")
    private Long updatedUtc;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private NewsData data;

}
