package org.raddan.newspaper.service;

import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author Alexander Dudkin
 */
@Component
public class ArticleFieldUpdater implements FieldUpdater<Article, ArticleDTO> {

    private static final Logger log = LoggerFactory.getLogger(ArticleFieldUpdater.class);

    @Override
    public Object update(Article article, ArticleDTO data) {
        for (Field field : article.getClass().getDeclaredFields()) {
            for (Field dtoField : data.getClass().getDeclaredFields()) {
                dtoField.setAccessible(true);
                if (dtoField.getName().equals(field.getName())) {
                    try {
                        field.setAccessible(true);
                        field.set(article, dtoField.get(data));
                    } catch (IllegalAccessException e) {
                        log.error("Error updating field: {}", e.getMessage());
                        throw new RuntimeException("Failed to update field", e);
                    }
                }
            }
        }

        log.info("Article: '{}' updated successfully!", article.getId());
        return article;
    }
}
