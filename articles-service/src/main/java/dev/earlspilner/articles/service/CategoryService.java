package dev.earlspilner.articles.service;

import dev.earlspilner.articles.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexander Dudkin
 */
public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(int id);
    Page<CategoryDto> getAllCategories(Pageable pageable);
    CategoryDto updateCategory(int id, CategoryDto categoryDto);
    void deleteCategory(int id);
}
