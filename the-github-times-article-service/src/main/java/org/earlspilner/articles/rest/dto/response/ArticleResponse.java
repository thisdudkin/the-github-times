package org.earlspilner.articles.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.earlspilner.users.model.Profile;

/**
 * @author Alexander Dudkin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Integer id;
    private Profile profile;
    private String title;
    private String summary;
    private String content;
    private String createdAt;
}
