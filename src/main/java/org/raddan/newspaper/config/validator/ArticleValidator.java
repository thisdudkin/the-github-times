package org.raddan.newspaper.config.validator;

import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Component
public class ArticleValidator implements Validator<Long> {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void validate(Long articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new ArticleNotFoundException("Article not found for ID: " + articleId);
        }
    }
}
