package dev.earlspilner.articles.mapper;

import dev.earlspilner.articles.dto.ArticleDto;
import dev.earlspilner.articles.dto.CategoryDto;
import dev.earlspilner.articles.dto.TagDto;
import dev.earlspilner.articles.entity.Article;
import dev.earlspilner.articles.entity.Category;
import dev.earlspilner.articles.entity.Tag;
import dev.earlspilner.articles.repository.ArticleRepository;
import dev.earlspilner.articles.repository.CategoryRepository;
import dev.earlspilner.articles.repository.TagRepository;
import dev.earlspilner.articles.service.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alexander Dudkin
 */
@Component
@RequiredArgsConstructor
public class ArticleMapper {

    private final TagRepository tagRepository;
    private final UserServiceClient userServiceClient;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public Article toEntity(ArticleDto dto) {
        if (dto == null) return null;

        Article article = new Article();
        article.setTitle(dto.title());
        article.setSummary(dto.summary());
        article.setContent(dto.content());

        Set<Category> categories = dto.categories().stream()
                .map(categoryDto -> categoryRepository.findByName(categoryDto.name())
                        .orElseGet(() -> {
                            Category category = new Category();
                            category.setName(categoryDto.name());
                            return categoryRepository.save(category);
                        })
                ).collect(Collectors.toSet());
        article.setCategories(categories);

        Set<Tag> tags = dto.tags().stream()
                .map(tagDto -> tagRepository.findByName(tagDto.name())
                        .orElseGet(() -> {
                            Tag tag = new Tag();
                            tag.setName(tagDto.name());
                            return tagRepository.save(tag);
                        })
                ).collect(Collectors.toSet());
        article.setTags(tags);

        if (dto.createdUtc() != null) {
            article.setCreatedUtc(Instant.parse(dto.createdUtc()));
        }
        if (dto.updatedUtc() != null) {
            article.setUpdatedUtc(Instant.parse(dto.updatedUtc()));
        }

        return article;
    }

    public ArticleDto toDto(Article article) {
        if (article == null) return null;

        Set<CategoryDto> categoryDtos = article.getCategories().stream()
                .map(category -> new CategoryDto(
                        category.getId(),
                        category.getName(),
                        null,
                        null
                ))
                .collect(Collectors.toSet());

        Set<TagDto> tagDtos = article.getTags().stream()
                .map(tag -> new TagDto(
                        tag.getId(),
                        tag.getName(),
                        null,
                        null
                ))
                .collect(Collectors.toSet());

        return new ArticleDto(
                article.getId(),
                article.getAuthorId(),
                article.getTitle(),
                article.getSummary(),
                article.getContent(),
                categoryDtos,
                tagDtos,
                article.getCreatedUtc() != null ? article.getCreatedUtc().toString() : null,
                article.getUpdatedUtc() != null ? article.getUpdatedUtc().toString() : null
        );
    }

}
