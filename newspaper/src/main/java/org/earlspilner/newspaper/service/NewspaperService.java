package org.earlspilner.newspaper.service;

import org.earlspilner.newspaper.model.Article;
import org.earlspilner.newspaper.model.Category;
import org.earlspilner.newspaper.model.Profile;
import org.earlspilner.newspaper.model.Tag;
import org.springframework.dao.DataAccessException;

import java.time.Instant;
import java.util.Collection;

/**
 * @author Alexander Dudkin
 */
public interface NewspaperService {

    Collection<Profile> findAllProfiles() throws DataAccessException;
    Profile findProfileById(int id) throws DataAccessException;
    Collection<Profile> findAllProfilesByFirstName(String firstName) throws DataAccessException;
    Collection<Profile> findAllProfilesByLastName(String lastName) throws DataAccessException;
    void updateProfile(Profile profile) throws DataAccessException;
    void saveProfile(Profile profile) throws DataAccessException;
    void deleteProfile(Profile profile) throws DataAccessException;

    Category findCategoryById(int id) throws DataAccessException;
    Category findCategoryByName(String name) throws DataAccessException;
    void saveCategory(Category category) throws DataAccessException;
    void updateCategory(Category category) throws DataAccessException;
    Collection<Category> findAllCategories() throws DataAccessException;
    void deleteCategory(Category category) throws DataAccessException;
    Tag findTagById(int id) throws DataAccessException;
    Tag findTagByName(String name) throws DataAccessException;
    void saveTag(Tag tag) throws DataAccessException;
    void updateTag(Tag tag) throws DataAccessException;
    Collection<Tag> findAllTags() throws DataAccessException;
    void deleteTag(Tag tag) throws DataAccessException;

    Collection<Article> findArticlesByDateRange(Instant startDate, Instant endDate) throws DataAccessException;
    Article findArticleById(int id) throws DataAccessException;
    Article findArticleByTitle(String title) throws DataAccessException;
    Collection<Article> findRecentArticles(int limit) throws DataAccessException;
    Collection<Article> findArticleByTagName(String tagName) throws DataAccessException;
    Collection<Article> findArticleByKeyword(String keyword) throws DataAccessException;
    Collection<Article> findArticleByCategoryName(String categoryName) throws DataAccessException;
    Collection<Article> findTopRatedArticles(int limit) throws DataAccessException;
    void saveArticle(Article article) throws DataAccessException;
    Collection<Article> findArticlesByCommentCount(int limit) throws DataAccessException;
    Collection<Article> findAllArticles() throws DataAccessException;
    void deleteArticle(Article article) throws DataAccessException;
    Collection<Article> findAllArticlesByAuthor(String author) throws DataAccessException;
    void updateArticle(Article article) throws DataAccessException;
    Collection<Article> findAllArticlesByCategory(Category category) throws DataAccessException;
    Collection<Article> findAllArticlesByTag(Tag tag) throws DataAccessException;
    Collection<Article> findMostViewedArticles() throws DataAccessException;


}
