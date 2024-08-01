package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.config.DataFetcherDeterminer;
import org.raddan.newspaper.config.EntityDeletionValidator;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.entity.Category;
import org.raddan.newspaper.entity.Tag;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.exception.custom.CategoryNotFoundException;
import org.raddan.newspaper.exception.custom.TagNotFoundException;
import org.raddan.newspaper.exception.custom.UnauthorizedException;
import org.raddan.newspaper.repository.ArticleRepository;
import org.raddan.newspaper.repository.CategoryRepository;
import org.raddan.newspaper.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author Alexander Dudkin
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityFieldUpdater fieldUpdater;

    @Autowired
    private EntityDeletionValidator entityDeletionValidator;
    @Autowired
    private DataFetcherDeterminer dataFetcherDeterminer;

    @Transactional
    public Article create(ArticleDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            throw new UnauthorizedException("You are not logged in to perform this action");

        Set<Category> categories = dto.getCategoryNames().stream()
                .map(categoryName -> categoryRepository.findByName(categoryName)
                        .orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + categoryName)))
                .collect(toSet());

        Set<Tag> tags = dto.getTagNames().stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseThrow(() -> new TagNotFoundException("Tag not found with name: " + tagName)))
                .collect(toSet());

        Article article = Article.builder()
                .user(currentUser)
                .title(dto.getTitle().trim())
                .summary(dto.getSummary().trim())
                .content(dto.getContent().trim())
                .picture(dto.getPicture().trim())
                .publishDate(Instant.now().getEpochSecond())
                .categories(categories)
                .tags(tags)
                .build();

        return articleRepository.save(article);
    }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));
    }

    @Transactional
    public Article update(Long id, ArticleDTO dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        fieldUpdater.update(article, dto);
        return articleRepository.save(article);
    }

    @Transactional
    public String delete(Long id) {
        User currentUser = userService.getCurrentUser();
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        if (!entityDeletionValidator.isValid(currentUser))
            throw new RuntimeException("You can not perform this action");

        articleRepository.delete(article);
        return "Article with id " + id + " has been deleted";
    }

    @Transactional
    public void incrementVisitCount(Long id) {
        Article article = getById(id);
        article.setVisitCount(article.getVisitCount() + 1);
        articleRepository.save(article);
    }

    public Set<Article> getAllNews(String param) {
        return param == null ? articleRepository.findAllOrderedByPublishDate()
                .orElseThrow(() -> new ArticleNotFoundException("Article not found")) : dataFetcherDeterminer.fetch(param);
    }
}
