package org.earlspilner.articles.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.earlspilner.articles.model.Article;
import org.earlspilner.users.model.Profile;

/**
 * @author Alexander Dudkin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Integer id;
    private Article article;
    private Profile profile;
    private String content;
    private String createdAt;
}
