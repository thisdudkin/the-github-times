package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.TagDTO;
import org.raddan.newspaper.entity.Tag;
import org.raddan.newspaper.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @GetMapping("/{name}")
    public Tag get(@PathVariable String name) {
        return tagService.getTagByName(name);
    }

    @PostMapping("/create")
    public Tag create(@RequestBody TagDTO dto) {
        return tagService.create(dto);
    }

    @PutMapping("/{name}/edit")
    public Tag update(@PathVariable String name, @RequestBody TagDTO dto) {
        return tagService.update(name, dto);
    }

    @DeleteMapping("/{name}/delete")
    public String delete(@PathVariable String name) {
        return tagService.delete(name);
    }

}
