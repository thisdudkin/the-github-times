package dev.earlspilner.articles.service;

import dev.earlspilner.articles.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexander Dudkin
 */
public interface TagService {
    TagDto addTag(TagDto tagDto);
    TagDto getTag(Integer id);
    Page<TagDto> getTags(Pageable pageable);
    TagDto updateTag(Integer id, TagDto tagDto);
    void deleteTag(Integer id);
}
