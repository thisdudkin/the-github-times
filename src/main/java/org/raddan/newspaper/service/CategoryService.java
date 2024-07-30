package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.CategoryDTO;
import org.raddan.newspaper.entity.Category;
import org.raddan.newspaper.exception.custom.CategoryAlreadyExistsException;
import org.raddan.newspaper.exception.custom.CategoryNotFoundException;
import org.raddan.newspaper.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityFieldUpdater fieldUpdater;

    @Transactional
    public Category create(CategoryDTO dto) {
        Optional<Category> optionalCategory = categoryRepository.findByName(dto.getName().trim());
        if (optionalCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category with that name already exists");
        }

        Category category = Category.builder()
                .name(dto.getName().trim())
                .build();

        return categoryRepository.save(category);
    }

    public Category get(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Transactional
    public Category update(String name, CategoryDTO dto) {
        Category category = get(name);
        fieldUpdater.update(category, dto);
        return categoryRepository.save(category);
    }

    @Transactional
    public String delete(String name) {
        Category category = get(name);
        categoryRepository.delete(category);
        return "Category '" + name + "' deleted";
    }

}
