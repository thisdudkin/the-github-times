package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.TagDto;
import org.raddan.newspaper.exception.custom.TagAlreadyExistsException;
import org.raddan.newspaper.exception.custom.TagNotFoundException;
import org.raddan.newspaper.model.Tag;
import org.raddan.newspaper.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
@Service
public class TagService {

    private final TagRepository tagRepository;
    private final EntityFieldUpdater fieldUpdater;

    @Autowired
    public TagService(TagRepository tagRepository,
                      EntityFieldUpdater fieldUpdater) {
        this.tagRepository = tagRepository;
        this.fieldUpdater = fieldUpdater;
    }

    public List<Tag> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        if (tags.isEmpty()) {
            throw new TagNotFoundException("Tags not found");
        }

        return tags;
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new TagNotFoundException("Tag not found"));
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name.trim())
                .orElseThrow(() -> new TagNotFoundException("Tag not found"));
    }

    @Transactional
    public Tag create(TagDto dto) {
        Optional<Tag> optionalTag = tagRepository.findByName(dto.getName().trim());
        if (optionalTag.isPresent()) {
            throw new TagAlreadyExistsException("Tag with that name already exists");
        }

        Tag tag = Tag.builder()
                .name(dto.getName().trim())
                .build();

        return tagRepository.save(tag);
    }

    @Transactional
    public Tag update(String name, TagDto dto) {
        Tag tag = getTagByName(name);
        fieldUpdater.update(tag, dto);
        return tagRepository.save(tag);
    }

    @Transactional
    public String delete(String name) {
        Tag tag = getTagByName(name);
        tagRepository.delete(tag);
        return "Tag '" + name.trim() + "' has been deleted";
    }

    public List<Tag> getTagsByName(List<String> tagNames) {
        List<Tag> tags = tagRepository.findByNameIn(tagNames);
        if (tags.isEmpty()) {
            throw new TagNotFoundException("Tags not found");
        }

        return tags;
    }

}
