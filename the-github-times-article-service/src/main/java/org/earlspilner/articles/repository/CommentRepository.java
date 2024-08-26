package org.earlspilner.articles.repository;

import org.earlspilner.articles.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
