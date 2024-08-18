package org.earlspilner.newspaper.service;

import org.earlspilner.newspaper.config.EntityUpdater;
import org.earlspilner.newspaper.model.Article;
import org.earlspilner.newspaper.model.Category;
import org.earlspilner.newspaper.model.Profile;
import org.earlspilner.newspaper.model.Tag;
import org.earlspilner.newspaper.repository.ArticleRepository;
import org.earlspilner.newspaper.repository.CategoryRepository;
import org.earlspilner.newspaper.repository.ProfileRepository;
import org.earlspilner.newspaper.repository.TagRepository;
import org.earlspilner.newspaper.rest.advice.custom.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
@Service
@ComponentScan(basePackages = "org.raddan")
public class NewspaperServiceImpl implements NewspaperService {

    private final TagRepository tagRepository;
    private final EntityUpdater entityUpdater;
    private final ProfileRepository profileRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public NewspaperServiceImpl(TagRepository tagRepository,
                                ProfileRepository profileRepository,
                                ArticleRepository articleRepository,
                                CategoryRepository categoryRepository, EntityUpdater entityUpdater) {
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.profileRepository = profileRepository;
        this.entityUpdater = entityUpdater;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Profile> findAllProfiles() throws DataAccessException {
        // TODO: Добавить пагинацию

        if (profileRepository.count() != 0) {
            return profileRepository.findAll();
        } else {
            throw new ProfileNotFoundException("Zero profiles were fetched from the database");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Profile findProfileById(int id) throws DataAccessException {
        // TODO: Кэширование популярных записей

        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isPresent()) {
            return optionalProfile.get();
        } else {
            throw new ProfileNotFoundException("Profile with id '" + id + "' not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Profile> findAllProfilesByFirstName(String firstName) throws DataAccessException {
        // TODO: Добавить пагинацию

        if (profileRepository.count() != 0) {
            return profileRepository.findAllProfilesByFirstName(firstName);
        } else {
            throw new ProfileNotFoundException("Zero profiles were fetched from the database");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Profile> findAllProfilesByLastName(String lastName) throws DataAccessException {
        // TODO: Добавить пагинацию

        if (profileRepository.count() != 0) {
            return profileRepository.findAllProfilesByLastName(lastName);
        } else {
            throw new ProfileNotFoundException("Zero profiles were fetched from the database");
        }
    }

    @Override
    @Transactional
    public void updateProfile(Profile profile) throws DataAccessException {
        Optional<Profile> optionalProfile = profileRepository.findById(profile.getId());
        if (optionalProfile.isPresent()) {
            Profile existingProfile = optionalProfile.get();
            entityUpdater.updateEntity(existingProfile, profile);

            profileRepository.save(existingProfile);
        } else {
            throw new ProfileNotFoundException("Profile with id '" + profile.getId() + "' not found");
        }
    }

    @Override
    @Transactional
    public void saveProfile(Profile profile) throws DataAccessException {
        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteProfile(Profile profile) throws DataAccessException {
        if (profileRepository.existsById(profile.getId())) {
            profileRepository.deleteById(profile.getId());
        } else {
            throw new ProfileNotFoundException("Profile with id '" + profile.getId() + "' not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Category findCategoryById(int id) throws DataAccessException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Category findCategoryByName(String name) throws DataAccessException {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new CategoryNotFoundException("Category not found with name: " + name);
        }
    }

    @Override
    @Transactional
    public void saveCategory(Category category) throws DataAccessException {
        if (!categoryRepository.existsByName(category.getName())) {
            categoryRepository.save(category);
        } else {
            throw new CategoryAlreadyExistException("Category already exist with name: " + category.getName());
        }
    }

    @Override
    @Transactional
    public void updateCategory(Category category) throws DataAccessException {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            entityUpdater.updateEntity(existingCategory, category);

            categoryRepository.save(existingCategory);
        } else {
            throw new CategoryNotFoundException("Category with id '" + category.getId() + "' not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Category> findAllCategories() throws DataAccessException {
        if (categoryRepository.count() != 0) {
            return categoryRepository.findAll();
        } else {
            throw new CategoryNotFoundException("Zero categories were fetched from the database");
        }
    }

    @Override
    @Transactional
    public void deleteCategory(Category category) throws DataAccessException {
        if (categoryRepository.existsByName(category.getName())) {
            categoryRepository.delete(category);
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + category.getId());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findTagById(int id) throws DataAccessException {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            return optionalTag.get();
        } else {
            throw new TagNotFoundException("Tag with id '" + id + "' not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findTagByName(String name) throws DataAccessException {
        Optional<Tag> optionalTag = tagRepository.findByName(name);
        if (optionalTag.isPresent()) {
            return optionalTag.get();
        } else {
            throw new TagNotFoundException("Tag with name '" + name + "' not found");
        }
    }

    @Override
    @Transactional
    public void saveTag(Tag tag) throws DataAccessException {
        if (!tagRepository.existsByName(tag.getName())) {
            tagRepository.save(tag);
        } else {
            throw new TagAlreadyExistException("Tag already exist with name: " + tag.getName());
        }
    }

    @Override
    @Transactional
    public void updateTag(Tag tag) throws DataAccessException {
        Optional<Tag> optionalTag = tagRepository.findById(tag.getId());
        if (optionalTag.isPresent()) {
            Tag existingTag = optionalTag.get();
            entityUpdater.updateEntity(existingTag, tag);
            tagRepository.save(existingTag);
        } else {
            throw new TagNotFoundException("Tag with id '" + tag.getId() + "' not found");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Tag> findAllTags() throws DataAccessException {
        // TODO: Добавить пагинацию

        if (tagRepository.count() != 0) {
            return tagRepository.findAll();
        } else {
            throw new TagNotFoundException("Zero tags were fetched from the database");
        }
    }

    @Override
    @Transactional
    public void deleteTag(Tag tag) throws DataAccessException {
        if (tagRepository.existsByName(tag.getName())) {
            tagRepository.delete(tag);
        } else {
            throw new TagNotFoundException("Tag not found with name: " + tag.getName());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Article> findArticlesByDateRange(Instant startDate, Instant endDate) throws DataAccessException {
        return articleRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Article findArticleById(int id) throws DataAccessException {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            return optionalArticle.get();
        } else {
            throw new ArticleNotFoundException("Article not found");
        }
    }

    @Override
    public Article findArticleByTitle(String title) throws DataAccessException {
        Optional<Article> optionalArticle = articleRepository.findByTitle(title);
        if (optionalArticle.isPresent()) {
            return optionalArticle.get();
        } else {
            throw new ArticleNotFoundException("Article not found");
        }
    }

    @Override
    public Collection<Article> findRecentArticles(int limit) throws DataAccessException {
        if (articleRepository.count() != 0) {
            return articleRepository.findRecentArticles();
        } else {
            throw new ArticleNotFoundException("Articles not found");
        }
    }

    @Override
    public Collection<Article> findArticlesByTagName(String tagName) throws DataAccessException {
        if (articleRepository.count() != 0) {
            return articleRepository.findArticlesByTagName(tagName);
        } else {
            throw new ArticleNotFoundException("Articles not found");
        }
    }

    @Override
    public Collection<Article> findArticlesByKeyword(String keyword) throws DataAccessException {
        if (articleRepository.count() != 0) {
            return articleRepository.findArticlesWithKeyword(keyword);
        } else {
            throw new ArticleNotFoundException("Articles not found");
        }
    }

    @Override
    public Collection<Article> findArticlesByCategoryName(String categoryName) throws DataAccessException {
        if (articleRepository.count() != 0) {
            return articleRepository.findArticlesByCategoryName(categoryName);
        } else {
            throw new CategoryNotFoundException("Category not found");
        }
    }

    @Override
    public Collection<Article> findTopRatedArticles(int limit) throws DataAccessException {
        // TODO
        return List.of();
    }

    @Override
    public void saveArticle(Article article) throws DataAccessException {
        if (!articleRepository.existsById(article.getId())) {
            articleRepository.save(article);
        } else {
            throw new ArticleAlreadyExistException("Articles already exist with ID: " + article.getId());
        }
    }

    @Override
    public Collection<Article> findArticlesByCommentCount(int limit) throws DataAccessException {
        return List.of();
    }

    @Override
    public Collection<Article> findAllArticles() throws DataAccessException {
        // TODO: Добавить пагинацию

        if (articleRepository.count() != 0) {
            return articleRepository.findAll();
        } else {
            throw new ArticleNotFoundException("Articles not found");
        }
    }

    @Override
    public void deleteArticle(Article article) throws DataAccessException {
        if (articleRepository.existsById(article.getId())) {
            articleRepository.delete(article);
        } else {
            throw new ArticleNotFoundException("Articles not found with ID: " + article.getId());
        }
    }

//    @Override
//    public Collection<Article> findAllArticlesByAuthor(String author) throws DataAccessException {
//        if (articleRepository.count() != 0) {
//            return articleRepository.findArticlesByAuthor(author);
//        } else {
//            throw new ArticleNotFoundException("Articles not found");
//        }
//    }

    @Override
    public void updateArticle(Article article) throws DataAccessException {
        Optional<Article> optionalArticle = articleRepository.findById(article.getId());
        if (optionalArticle.isPresent()) {
            entityUpdater.updateEntity(optionalArticle.get(), article);
            articleRepository.save(article);
        } else {
            throw new ArticleNotFoundException("Article not found with ID: " + article.getId());
        }
    }

    @Override
    public Collection<Article> findAllArticlesByCategory(Category category) throws DataAccessException {
        return List.of();
    }

    @Override
    public Collection<Article> findAllArticlesByTag(Tag tag) throws DataAccessException {
        return List.of();
    }

    @Override
    public Collection<Article> findMostViewedArticles() throws DataAccessException {
        return List.of();
    }
}