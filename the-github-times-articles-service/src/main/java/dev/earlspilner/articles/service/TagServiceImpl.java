package dev.earlspilner.articles.service;

import dev.earlspilner.articles.advice.TagNotFoundException;
import dev.earlspilner.articles.config.FieldUpdater;
import dev.earlspilner.articles.dto.TagDto;
import dev.earlspilner.articles.entity.Tag;
import dev.earlspilner.articles.mapper.TagMapper;
import dev.earlspilner.articles.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;
    private final FieldUpdater fieldUpdater;
    private final TagRepository tagRepository;

    @Override
    public TagDto addTag(TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        tagRepository.save(tag);
        return tagMapper.toDto(tag);
    }

    @Override
    public TagDto getTag(Integer id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + id));

        return tagMapper.toDto(tag);
    }

    @Override
    public Page<TagDto> getTags(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);
        return tags.map(tagMapper::toDto);
    }

    @Override
    public TagDto updateTag(Integer id, TagDto tagDto) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + id));

        fieldUpdater.update(tag, tagDto);
        tagRepository.save(tag);
        return tagMapper.toDto(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + id));

        tagRepository.deleteById(tag.getId());
    }
}
