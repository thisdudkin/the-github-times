package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.config.validator.EntityDeletionValidator;
import org.raddan.newspaper.dto.ArticleDto;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.exception.custom.UnauthorizedException;
import org.raddan.newspaper.model.Article;
import org.raddan.newspaper.model.Category;
import org.raddan.newspaper.model.Tag;
import org.raddan.newspaper.model.User;
import org.raddan.newspaper.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Service
public class ArticleService {

    private final CategoryService categoryService;
    private final TagService tagService;
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final EntityFieldUpdater fieldUpdater;
    private final EntityDeletionValidator entityDeletionValidator;

    @Autowired
    public ArticleService(CategoryService categoryService,
                          TagService tagService,
                          ArticleRepository articleRepository,
                          UserService userService,
                          EntityFieldUpdater fieldUpdater,
                          EntityDeletionValidator entityDeletionValidator) {
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.articleRepository = articleRepository;
        this.userService = userService;
        this.fieldUpdater = fieldUpdater;
        this.entityDeletionValidator = entityDeletionValidator;
    }

    public List<Article> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("Articles not found");
        }

        return articles;
    }

    public Article getById(Long id) {
        return findArticleById(id);
    }

    @Transactional
    public Article create(ArticleDto dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            throw new UnauthorizedException("You are not logged in to perform this action");

        List<Category> categories = categoryService.getCategoriesByName(dto.getCategoryNames());
        List<Tag> tags = tagService.getTagsByName(dto.getTagNames());

        Article article = buildArticle(dto, currentUser, categories, tags);
        return articleRepository.save(article);
    }

    @Transactional
    public Article update(Long id, ArticleDto dto) {
        Article article = findArticleById(id);

        fieldUpdater.update(article, dto);
        return articleRepository.save(article);
    }

    @Transactional
    public String delete(Long id) {
        User currentUser = userService.getCurrentUser();
        Article article = findArticleById(id);

        if (!entityDeletionValidator.isValid(currentUser))
            throw new RuntimeException("You can not perform this action");

        articleRepository.delete(article);
        return "Article with id " + id + " has been deleted";
    }

    private Article buildArticle(ArticleDto dto,
                                 User currenctUser,
                                 List<Category> categories,
                                 List<Tag> tags) {
        return Article.builder()
                .user(currenctUser)
                .title(dto.getTitle().trim())
                .summary(dto.getSummary().trim())
                .content(dto.getContent().trim())
                .picture(dto.getPicture().trim())
                .publishDate(Instant.now())
                .categories(categories)
                .tags(tags)
                .build();
    }

    private Article findArticleById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));
    }
}
