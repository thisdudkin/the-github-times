package dev.earlspilner.articles.mapper;

import dev.earlspilner.articles.dto.ArticleDto;
import dev.earlspilner.articles.entity.Article;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toEntity(ArticleDto articleDto);
    ArticleDto toDto(Article article);
}
