package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.config.EntityDeletionValidator;
import org.raddan.newspaper.config.updater.EntityFieldUpdater;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.model.Article;
import org.raddan.newspaper.model.Category;
import org.raddan.newspaper.model.Tag;
import org.raddan.newspaper.model.User;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.exception.custom.UnauthorizedException;
import org.raddan.newspaper.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final CategoryService categoryService;
    private final TagService tagService;
    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final EntityFieldUpdater fieldUpdater;
    private final EntityDeletionValidator entityDeletionValidator;

    public List<Article> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("Articles not found");
        }

        return articles;
    }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));
    }

    @Transactional
    public Article create(ArticleDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            throw new UnauthorizedException("You are not logged in to perform this action");

        List<Category> categories = categoryService.getCategoriesByName(dto.getCategoryNames());
        List<Tag> tags = tagService.getTagsByName(dto.getTagNames());

        Article article = buildArticle(dto, currentUser, categories, tags);
        return articleRepository.save(article);
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

    private Article buildArticle(ArticleDTO dto,
                                 User currenctUser,
                                 List<Category> categories,
                                 List<Tag> tags) {
        return Article.builder()
                .user(currenctUser)
                .title(dto.getTitle().trim())
                .summary(dto.getSummary().trim())
                .content(dto.getContent().trim())
                .picture(dto.getPicture().trim())
                .publishDate(Instant.now().getEpochSecond())
                .categories(categories)
                .tags(tags)
                .build();
    }
}
