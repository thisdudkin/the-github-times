package dev.earlspilner.articles.mapper;

import dev.earlspilner.articles.dto.TagDto;
import dev.earlspilner.articles.entity.Tag;
import org.mapstruct.Mapper;

/**
 * @author Alexander Dudkin
 */
@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toEntity(TagDto tagDto);
    TagDto toDto(Tag tag);
}
