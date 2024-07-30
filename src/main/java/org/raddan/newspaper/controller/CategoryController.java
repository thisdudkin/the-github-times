package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.CategoryDTO;
import org.raddan.newspaper.entity.Category;
import org.raddan.newspaper.service.CategoryService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/new")
    public Category create(@RequestBody CategoryDTO dto) {
        return categoryService.create(dto);
    }

    @GetMapping("/{name}")
    public Category get(@PathVariable String name) {
        return categoryService.get(name);
    }

    @PutMapping("/{name}")
    public Category update(@PathVariable String name, @RequestBody CategoryDTO dto) {
        return categoryService.update(name, dto);
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable String name) {
        return categoryService.delete(name);
    }

}
