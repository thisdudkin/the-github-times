package org.raddan.newspaper.controller;

import org.raddan.newspaper.dto.TagDto;
import org.raddan.newspaper.model.Tag;
import org.raddan.newspaper.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

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
    public Tag create(@RequestBody TagDto dto) {
        return tagService.create(dto);
    }

    @PutMapping("/{name}/edit")
    public Tag update(@PathVariable String name, @RequestBody TagDto dto) {
        return tagService.update(name, dto);
    }

    @DeleteMapping("/{name}/delete")
    public String delete(@PathVariable String name) {
        return tagService.delete(name);
    }

}
