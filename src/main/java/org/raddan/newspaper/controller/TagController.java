package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.TagDTO;
import org.raddan.newspaper.entity.Tag;
import org.raddan.newspaper.service.TagService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping("/new")
    public Tag create(@RequestBody TagDTO dto) {
        return tagService.create(dto);
    }

    @GetMapping("/{name}")
    public Tag get(@PathVariable String name) {
        return tagService.get(name);
    }

    @PutMapping("/{name}")
    public Tag update(@PathVariable String name, @RequestBody TagDTO dto) {
        return tagService.update(name, dto);
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable String name) {
        return tagService.delete(name);
    }

}
