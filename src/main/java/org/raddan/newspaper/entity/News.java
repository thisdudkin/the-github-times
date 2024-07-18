package org.raddan.newspaper.entity;

import jakarta.persistence.*;
import lombok.*;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.data.NewsDataConverter;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "news_id_seq", sequenceName = "news_id_seq", allocationSize = 1)
    @Column(name = "news_id", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "created_utc", nullable = false)
    private Long createdUtc;

    @Column(name = "updated_utc")
    private Long updatedUtc;

    @Convert(converter = NewsDataConverter.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private NewsData data;

}
