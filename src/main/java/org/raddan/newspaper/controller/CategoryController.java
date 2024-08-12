package org.raddan.newspaper.controller;

import org.raddan.newspaper.dto.CategoryDto;
import org.raddan.newspaper.model.Category;
import org.raddan.newspaper.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/id")
    public Category getCategoryById(@RequestParam Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{name}")
    public Category get(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }

    @PostMapping("/create")
    public Category create(@RequestBody CategoryDto dto) {
        return categoryService.create(dto);
    }

    @PutMapping("/{name}/edit")
    public Category update(@PathVariable String name,
                           @RequestBody CategoryDto dto) {
        return categoryService.update(name, dto);
    }

    @DeleteMapping("/{name}/delete")
    public String delete(@PathVariable String name) {
        return categoryService.delete(name);
    }

}
