package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.exception.custom.UnauthorizedException;
import org.raddan.newspaper.repository.ArticleRepository;
import org.raddan.newspaper.repository.CategoryRepository;
import org.raddan.newspaper.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Alexander Dudkin
 */
@Service
public class ArticleService implements EntityService<Article, ArticleDTO> {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleFieldUpdater fieldUpdater;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    @Transactional
    public Article create(ArticleDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            throw new UnauthorizedException("You are not logged in to perform this action");

        Article article = Article.builder()
                .user(currentUser)
                .title(dto.getTitle())
                .summary(dto.getSummary())
                .content(dto.getContent())
                .picture(dto.getPicture())
                .publishDate(Instant.now().getEpochSecond())
                .categories(categoryRepository.findAllById(dto.getCategoryIds()))
                .tags(tagRepository.findAllById(dto.getTagIds()))
                .build();
        articleRepository.save(article);
        return article;
    }

    @Override
    public Article get() {
        throw new UnsupportedOperationException("Method not supported yet");
    }

    @Override
    public Article update(ArticleDTO dto) {
        throw new UnsupportedOperationException("Method not supported yet");
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Method not supported yet");
    }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));
    }

    public Article updateById(ArticleDTO dto, Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        fieldUpdater.update(article, dto);
        articleRepository.save(article);
        return article;
    }

    public String deleteById(Long id) {
        Article article = getById(id);
        articleRepository.delete(article);
        return "Article '" + article.getId() + "' has been deleted at: " + LocalDateTime.now();
    }
}
