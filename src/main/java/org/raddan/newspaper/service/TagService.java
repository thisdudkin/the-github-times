package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.TagDTO;
import org.raddan.newspaper.entity.Tag;
import org.raddan.newspaper.exception.custom.TagAlreadyExistsException;
import org.raddan.newspaper.exception.custom.TagNotFoundException;
import org.raddan.newspaper.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author Alexander Dudkin
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EntityFieldUpdater fieldUpdater;

    @Transactional
    public Tag create(TagDTO dto) {
        Optional<Tag> optionalTag = tagRepository.findByName(dto.getName().trim());
        if (optionalTag.isPresent()) {
            throw new TagAlreadyExistsException("Tag with that name already exists");
        }

        Tag tag = Tag.builder()
                .name(dto.getName().trim())
                .build();

        return tagRepository.save(tag);
    }

    public Tag getByName(String name) {
        return tagRepository.findByName(name.trim())
                .orElseThrow(() -> new TagNotFoundException("Tag not found"));
    }

    @Transactional
    public Tag update(String name, TagDTO dto) {
        Tag tag = getByName(name);
        fieldUpdater.update(tag, dto);
        return tagRepository.save(tag);
    }

    @Transactional
    public String delete(String name) {
        Tag tag = getByName(name);
        tagRepository.delete(tag);
        return "Tag '" + name.trim() + "' has been deleted";
    }

    public Set<Tag> getTagsByName(Set<String> tagNames) {
        return tagNames.stream()
                .map(this::getByName)
                .collect(toSet());
    }
}
