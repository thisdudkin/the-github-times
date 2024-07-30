package org.raddan.newspaper.service;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.auth.service.UserService;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.entity.Category;
import org.raddan.newspaper.entity.Tag;
import org.raddan.newspaper.entity.User;
import org.raddan.newspaper.exception.custom.ArticleAlreadyExistsException;
import org.raddan.newspaper.exception.custom.UnauthorizedException;
import org.raddan.newspaper.repository.ArticleRepository;
import org.raddan.newspaper.repository.CategoryRepository;
import org.raddan.newspaper.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

    @Transactional
    public Article create(ArticleDTO dto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null)
            throw new UnauthorizedException("You are not logged in to perform this action");

        Article optionalArticle = articleRepository.findByTitle(dto.getTitle().trim())
                .orElseThrow(() -> new ArticleAlreadyExistsException("Article with the same title exists"));

        List<Category> categories = categoryRepository.findAllById(dto.getCategoryIds());
        List<Tag> tags = tagRepository.findAllById(dto.getTagIds());

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

}
