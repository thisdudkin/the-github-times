package dev.earlspilner.articles.mapper;

import dev.earlspilner.articles.dto.CategoryDto;
import dev.earlspilner.articles.entity.Category;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
