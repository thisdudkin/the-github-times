package dev.earlspilner.articles.service;

import dev.earlspilner.articles.advice.ArticleNotFoundException;
import dev.earlspilner.articles.config.FieldUpdater;
import dev.earlspilner.articles.dto.ArticleDto;
import dev.earlspilner.articles.dto.UserDto;
import dev.earlspilner.articles.entity.Article;
import dev.earlspilner.articles.mapper.ArticleMapper;
import dev.earlspilner.articles.repository.ArticleRepository;
import dev.earlspilner.articles.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final JwtUtil jwtUtil;
    private final FieldUpdater fieldUpdater;
    private final ArticleMapper articleMapper;
    private final ArticleRepository articleRepository;
    private final UserServiceClient userServiceClient;

    @Override
    public ArticleDto addArticle(HttpServletRequest request, ArticleDto articleDto) {
        UserDto user = userServiceClient.getUserByUsername(jwtUtil.getUsername(jwtUtil.resolveToken(request)));
        Article article = articleMapper.toEntity(articleDto);

        article.setAuthorId(user.id());
        article.getCategories().forEach(category -> article.getCategories().add(category));
        article.getTags().forEach(tag -> article.getTags().add(tag));

        articleRepository.save(article);
        return articleMapper.toDto(article);
    }

    @Override
    public ArticleDto getArticle(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));

        return articleMapper.toDto(article);
    }

    @Override
    public Page<ArticleDto> getArticles(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(articleMapper::toDto);
    }

    @Override
    public ArticleDto updateArticle(Integer id, ArticleDto articleDto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));

        fieldUpdater.update(article, articleDto);
        articleRepository.save(article);
        return articleMapper.toDto(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with ID: " + id));

        articleRepository.deleteById(article.getId());
    }
}
